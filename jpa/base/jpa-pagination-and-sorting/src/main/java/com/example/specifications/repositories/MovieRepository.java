package com.example.specifications.repositories;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */
import com.example.specifications.domains.Movie;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long>,
        JpaSpecificationExecutor<Movie> {

    // TODO: add queries
}
