package com.se.images.repo;

import com.se.images.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepo extends JpaRepository<Tag, Long> {

    List<Tag> findAll();

    @Query("Select distinct i.tag from Tag i")
    List<String> findAllTags();

    @Query("Select distinct i.tag from Tag i where i.tag like %:filter%")
    List<String> findAllTagsLike(@Param("filter") String filter);

    @Query("select i from Tag i where i.tag =:filter")
    Optional<Tag> findAllByTagLike(@Param("filter") String filter);

    void deleteById(@Param("id") Long id);
}