package com.se.sample.rest.repository;

import com.se.sample.rest.entity.Position;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends ReactiveSortingRepository<Position, String> {

}
