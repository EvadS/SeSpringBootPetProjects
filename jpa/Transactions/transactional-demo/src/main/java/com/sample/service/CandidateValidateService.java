package com.sample.service;

import com.sample.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

@Service
public class CandidateValidateService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private CandidateRepository repository;

    // will be executed in a separate transaction. So, the parent one will not be rollbacked.
    @Transactional(noRollbackFor = IllegalArgumentException.class)
    public void validateName(String name) {
        if (name == null || !StringUtils.hasText(name) || repository.existsByName(name)) {
            throw new IllegalArgumentException("name is forbidden");
        }
    }



}
