package com.se.images.repo;

import com.se.images.entities.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {

    Page<Image> findAll(Pageable pageable);

    @Query("select i from Image i inner join ImageTag j on i.id = j.image.id inner join Tag k on j.tag.id = k.id  where k.tag = :filter")
    Page<Image> findAllByTagLike(@Param("filter") String filter, Pageable pageable);

    void deleteById(@Param("id") Long id);
}
