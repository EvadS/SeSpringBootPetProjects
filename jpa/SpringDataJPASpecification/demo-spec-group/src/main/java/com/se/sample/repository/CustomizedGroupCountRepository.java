package com.se.sample.repository;

import com.se.sample.model.AlarmMsg;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.metamodel.SingularAttribute;
import java.util.Map;

public interface CustomizedGroupCountRepository {
    Map<AlarmMsg.AlarmLevel, Long> groupAndCount(SingularAttribute singularAttribute, Specification where);

}
