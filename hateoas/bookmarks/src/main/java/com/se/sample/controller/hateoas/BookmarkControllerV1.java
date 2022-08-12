package com.se.sample.controller.hateoas;

import com.se.sample.entity.Bookmark;
import com.se.sample.exception.UserNotFoundException;
import com.se.sample.model.resource.BookmarkResource;
import com.se.sample.model.resource.BookmarkResourceList;
import com.se.sample.repository.AccountRepository;
import com.se.sample.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/{userId}/bookmark-v1")
public class BookmarkControllerV1 {
    private final BookmarkRepository bookmarkRepository;

    private final AccountRepository accountRepository;

    @Autowired
    public BookmarkControllerV1(AccountRepository accountRepository, BookmarkRepository bookmarkRepository) {
        this.accountRepository = accountRepository;
        this.bookmarkRepository = bookmarkRepository;
    }


    @RequestMapping(value = "/{bookmarkId}", method = RequestMethod.GET)
    public BookmarkResource readBookmark(@PathVariable Long userId, @PathVariable Long bookmarkId) {
        this.validateUser(userId);

        Bookmark bookmark = bookmarkRepository.getOne(bookmarkId);

        BookmarkResource resource = new BookmarkResource(bookmark);
        return resource;
    }


    @RequestMapping(method = RequestMethod.POST,consumes = "application/json")
    ResponseEntity<?> add(@PathVariable Long userId, @RequestBody Bookmark input) {

        this.validateUser(userId);

        return accountRepository.findById(userId)
                .map(account -> {
                            Bookmark bookmark = bookmarkRepository.save(new Bookmark(account, input.uri, input.description));

                            HttpHeaders httpHeaders = new HttpHeaders();

                            Link forOneBookmark = new BookmarkResource(bookmark).getLink("self").get();


                            httpHeaders.setLocation(URI.create(forOneBookmark.getHref()));

                            return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
                        }
                ).get();
    }


    // TODO: List<BookmarkResource> -> one object with list as a field
    @RequestMapping(method = RequestMethod.GET)
    EntityModel<BookmarkResourceList> readBookmarks(@PathVariable Long userId) {

        this.validateUser(userId);

        BookmarkResourceList bookmarkResourceList = new BookmarkResourceList();
        Collection<Bookmark> bookmarkList = bookmarkRepository.findBookmarkByAccount_Id(userId);


        List<BookmarkResource> collect = bookmarkList.stream()
                //.map(x -> new BookmarkResource(x))
                .map(BookmarkResource::new)
                .collect(Collectors.toList());


        for ( BookmarkResource resource : collect             ) {
            bookmarkResourceList.getList().add(resource);
        }

        return new EntityModel<>(bookmarkResourceList);
    }


    private void validateUser(Long userId) {
        this.accountRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("%s not found ", userId)));
    }
}
