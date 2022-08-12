package com.se.sample.controller;

import com.se.sample.entity.Account;
import com.se.sample.entity.Bookmark;
import com.se.sample.exception.UserNotFoundException;
import com.se.sample.model.input.AccountModel;
import com.se.sample.model.input.AccountUpdateModel;
import com.se.sample.model.response.AccountList;
import com.se.sample.model.response.AccountResponse;
import com.se.sample.repository.AccountRepository;
import com.se.sample.repository.BookmarkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountRepository accountRepository;

    private final BookmarkRepository bookmarkRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    public AccountController(AccountRepository accountRepository, BookmarkRepository bookmarkRepository) {
        this.accountRepository = accountRepository;
        this.bookmarkRepository = bookmarkRepository;
    }

    @GetMapping("/list")
    public ResponseEntity<AccountList> list() {
        return ResponseEntity.ok(new AccountList(accountRepository.findAll()));
    }

    @GetMapping("/list-base")
    public List<AccountResponse> listBase() {
        return accountRepository
                .findAll()
                .stream().map(AccountResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Account get(@PathVariable(name = "id") Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("Account %postId  not found", id)));

    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<AccountResponse> createAccount(@NotNull @Valid @RequestBody AccountModel accountModel) {

        Account account = (accountRepository.save(convertToEntity(accountModel)));
        return new ResponseEntity<>(new AccountResponse(account), HttpStatus.CREATED);
    }

    @PutMapping(value = "/account/{id}", consumes = "application/json")
    public Account updatePost(@PathVariable(value = "id") Long id,
                              @Valid @RequestBody AccountModel account) {

        if (!accountRepository.existsById(id)) {
            throw new UserNotFoundException(String.format("Account %postId  not found", id));
        }

        return accountRepository.findById(id).map(ac -> {
            ac.setPassword(account.getPassword());
            ac.setUsername(account.getUsername());
            ac.setBookmarks(ac.getBookmarks());

            return accountRepository.save(ac);
        }).orElseThrow(() -> new UserNotFoundException(String.format("Account %postId  not found", id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        // TODO: validate account
        Collection<Bookmark> bookmarks = bookmarkRepository.findBookmarkByAccount_Id(id);

        if (!bookmarks.isEmpty()) {

            for (Bookmark bookmark : bookmarks) {
                bookmarkRepository.delete(bookmark);
            }
        }

        return accountRepository.findById(id).map(post -> {
            accountRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new UserNotFoundException(String.format("Account %postId  not found", id)));
    }


    private Account convertToEntity(AccountModel account) {
        Account post = modelMapper.map(account, Account.class);

        post.setUsername(account.getUsername());
        post.setPassword(account.getPassword());

        return post;
    }

    private Account convertToEntity(AccountUpdateModel account) throws ParseException {
        Account post = modelMapper.map(account, Account.class);

        if (account.getId() != null) {
            Account oldAccount = accountRepository.getAccountById(account.getId());
            post.setUsername(oldAccount.getUsername());
            post.setPassword(oldAccount.getPassword());
        }
        return post;
    }
}
