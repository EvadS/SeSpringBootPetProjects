package com.se.sample.service.impl;

import com.se.sample.model.Employee;
import com.se.sample.repository.EmployeeRepository;
import com.se.sample.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Skiba Evgeniy
 * @date 28.08.2021
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepo;

    @Override
    public void create(Employee employee) {
//        return this.profileRepository
//                .save(new Profile(null, email))
//                .doOnSuccess(profile -> this.publisher.publishEvent(new ProfileCreatedEvent(profile)));


        employeeRepo.save(employee).subscribe();
    }

    @Override
    public Mono<Employee> findById(Integer id) {
        return employeeRepo.findById(id);
    }

    @Override
    public Flux<Employee> findByName(String name) {
        return employeeRepo.findByName(name);
    }

    @Override
    public Flux<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Mono<Employee> update(Employee e) {

//        return this.profileRepository
//                .findById(id)
//                .map(p -> new Profile(p.getId(), email))
//                .flatMap(this.profileRepository::save);

        return employeeRepo.save(e);
    }

    @Override
    public Mono<Void> delete(Integer id) {
//        return this.profileRepository
//                .findById(id)
//                .flatMap(p -> this.profileRepository.deleteById(p.getId()).thenReturn(p));

        return employeeRepo.deleteById(id);
    }
}
