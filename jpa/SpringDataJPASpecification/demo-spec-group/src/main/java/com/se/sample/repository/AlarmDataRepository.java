package com.se.sample.repository;

import com.se.sample.model.AlarmData;
import com.se.sample.model.AlarmLevel;
import com.se.sample.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface AlarmDataRepository extends JpaRepository<AlarmData, Long>, JpaSpecificationExecutor<AlarmData>, CustomizedGroupCountRepository {

}
