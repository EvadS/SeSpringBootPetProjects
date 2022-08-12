package com.se.sample.service;

import com.se.sample.model.*;
import com.se.sample.repository.AlarmDataRepository;
import com.se.sample.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

@Autowired
    EntityManager entityManager;

    public void test1(){

    }


    public static Specification<Employee> personWorksIn(final String companyName) {
//        return new Specification<Employee>() {
//            @Override
//            public Predicate toPredicate(Root<Employee> root,
//                                         CriteriaQuery<?> criteriaQuery,
//                                         CriteriaBuilder criteriaBuilder) {
//                Join<Person, Company> company = root.join("workingPlaces");
//                return criteriaBuilder.equal(company.get("name"), companyName);
//            }
//        };

        return null;
    }


    @Autowired
    AlarmDataRepository alarmDataRepository;

    public Map<AlarmMsg.AlarmLevel, Long> testSpecification() {

        SingularAttribute attribute = AlarmData_.isClear;
        Specification<Object> where = Specification.where(
                (root, query, cb) -> cb.equal(root.get(attribute), false)
        );

        final Map<AlarmMsg.AlarmLevel, Long> result = alarmDataRepository.groupAndCount(AlarmData_.alarmLevel, where );
        return result;
    }

}
