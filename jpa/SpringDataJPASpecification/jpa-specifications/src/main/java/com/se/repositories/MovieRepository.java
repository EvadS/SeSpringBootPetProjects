package com.se.repositories;

import com.se.domains.Movie;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long>,
        JpaSpecificationExecutor<Movie> {

    // TODO: add queries
}
