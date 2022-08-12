package com.se.sample.repository;

import com.se.sample.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.stream.Collectors;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Collection<Bookmark> findByAccountUsername(String username);

    Collection<Bookmark> findBookmarkByAccount_Id(Long account_Id);
}
