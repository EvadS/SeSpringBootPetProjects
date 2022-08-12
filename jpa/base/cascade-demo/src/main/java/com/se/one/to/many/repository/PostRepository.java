package com.se.one.to.many.repository;



import com.se.one.to.many.entity.PostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostItem, Long> {

}
