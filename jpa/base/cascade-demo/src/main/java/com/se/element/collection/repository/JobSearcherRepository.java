package com.se.element.collection.repository;

import com.se.element.collection.model.JobSearcher;
import com.se.element.collection.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobSearcherRepository extends JpaRepository<JobSearcher, Long> {

}