package com.se.many.to.many.demo2.repo;


import com.se.many.to.many.demo2.entity.Searcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearcherRepository extends JpaRepository<Searcher, Long> {

}