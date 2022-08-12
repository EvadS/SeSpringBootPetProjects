package com.se.sample.repository;

import com.se.sample.persist.RemoteServerTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemoteServerRepository extends JpaRepository<RemoteServerTaskEntity, Long> {
}
