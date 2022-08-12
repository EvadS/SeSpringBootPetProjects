package com.se.nil.embedded.repository;

import com.se.nil.embedded.entity.ID;
import com.se.nil.embedded.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Office, ID> {
}
