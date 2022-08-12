package com.se.maual.pagging.repository;

import com.se.maual.pagging.model.MonstaUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */
@Repository
public interface MonstaUsersRepository extends JpaRepository<MonstaUsers, Long> {
}
