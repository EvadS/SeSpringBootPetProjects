package com.se.maual.pagging.service;

import com.se.maual.pagging.model.MonstaUsers;
import org.springframework.data.domain.Page;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */
public interface MonstaUsersService {
    Page<MonstaUsers> getUsers(String orderBy, String direction, int page, int size);
}
