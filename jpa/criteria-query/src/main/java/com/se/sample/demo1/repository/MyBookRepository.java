package com.se.sample.demo1.repository;

import com.se.sample.demo1.domain.MyBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import java.awt.print.Book;
import java.util.List;

@Repository
public interface MyBookRepository extends JpaRepository<MyBook, Long>, JpaSpecificationExecutor<MyBook> {



}
