package com.se.sample.service;

import com.se.sample.model.*;
import com.se.sample.repository.AlarmDataRepository;
import com.se.sample.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import javax.persistence.metamodel.SingularAttribute;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

//    public  void findEmployeeCountGroupByDept() {
////        System.out.println("-- Employee count group by dept --");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
//        Root<Employee> employee = query.from(Employee.class);
////
//
//        query.multiselect(employee.get(Employee_.dept), criteriaBuilder.count(employee));
//        TypedQuery<Object[]> typedQuery = entityManager.createQuery(query);
//        List<Object[]> resultList = typedQuery.getResultList();
//        resultList.forEach(objects ->
//                System.out.printf("Dept: %s, Count: %s%n", objects[0], objects[1]));
////
//    }



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
