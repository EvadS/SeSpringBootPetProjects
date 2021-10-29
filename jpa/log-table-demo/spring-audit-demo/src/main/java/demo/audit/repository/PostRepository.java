package demo.audit.repository;

import demo.audit.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends RevisionRepository<Post, Integer, Integer>, JpaRepository<Post, Integer>{

}
