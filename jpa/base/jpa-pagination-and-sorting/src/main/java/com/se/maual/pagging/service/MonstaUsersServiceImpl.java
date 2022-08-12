package com.se.maual.pagging.service;

import com.se.maual.pagging.model.MonstaUsers;
import com.se.maual.pagging.repository.MonstaUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */

@Service
public class MonstaUsersServiceImpl implements MonstaUsersService {
    @Autowired
    private MonstaUsersRepository monstaUsersRepository;

    @Override
    public Page<MonstaUsers> getUsers(String orderBy, String direction, int page, int size) {
        Sort sort = null;
        if (direction.equals("asc")) {
            sort = Sort.by(Sort.Direction.ASC, orderBy);
        }
        if (direction.equals("desc")) {
            sort = Sort.by(Sort.Direction.ASC, orderBy);
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<MonstaUsers> data = monstaUsersRepository.findAll(pageable);
        return data;
    }
}
