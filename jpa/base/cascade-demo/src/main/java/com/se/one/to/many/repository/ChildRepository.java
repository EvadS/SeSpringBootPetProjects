package com.se.one.to.many.repository;


import com.se.one.to.many.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {

}