package com.se.customizing.jpa.repository;


import com.se.customizing.jpa.entity.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Employee> getFirstNamesLike(String firstName) {
        Query query = entityManager.createNativeQuery("SELECT em.* FROM spring_data_jpa_example.employee as em " +
                "WHERE em.firstname LIKE ?", Employee.class);
        query.setParameter(1, firstName + "%");
        return query.getResultList();
    }
}