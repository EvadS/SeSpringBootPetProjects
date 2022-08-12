package com.se.sample;

import com.se.sample.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CoommentRepository extends JpaRepository<Comment,Long>, JpaSpecificationExecutor<Comment> {
}
