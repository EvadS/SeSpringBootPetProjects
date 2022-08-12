package com.se.images.repo;

import com.se.images.entities.ImageTag;
import com.se.images.entities.ImageTagId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ImageTagRepo extends JpaRepository<ImageTag, ImageTagId> {
    List<ImageTag> findAllByImageId(Long imageID);

}
