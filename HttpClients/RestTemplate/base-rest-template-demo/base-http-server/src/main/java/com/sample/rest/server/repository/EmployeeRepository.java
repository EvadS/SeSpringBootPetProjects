package com.sample.rest.server.repository;

import com.sample.rest.server.model.domain.EmployeeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeVO, Long> {
    List<EmployeeVO> findAll();

    EmployeeVO getByEmployeeId(Long id);
}
