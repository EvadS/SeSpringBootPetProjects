package com.se.account.repository;


import com.se.account.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    default Customer findOne(Long id) {
        return (Customer) findById(id).orElse(null);
    }
}
