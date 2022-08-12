package com.se.sample.components;

import com.se.sample.domain.Fixture;
import com.se.sample.domain.Sport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {


    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    EntityManagerFactory entityManagerFactory;

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        logger.info("************** HERE ");

        //basePredicateTest();

       // baseJoin();

         baseJoin2();

    }

    private void baseJoin2() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        //открываем транзакцию и создаём CriteriaBuilder который будет строить объекты запросов
        CriteriaBuilder cb = em.getCriteriaBuilder();

        // параметризуется типом, который этот запрос возвращаем
        CriteriaQuery<Sport> criteriaQuery = cb.createQuery(Sport.class);
        Root<Sport> fixtureRoot = criteriaQuery.from(Sport.class);


        CriteriaQuery<Object> cq = cb.createQuery(Object.class);
        Subquery<Sport> subcq = cq.subquery(Sport.class);
        subcq.select(fixtureRoot.get("name"));
       // subcq.where(** complex where statements **);
        subcq.groupBy(fixtureRoot.get("name"));
        cq.select(subcq); //cb.avg(subcq));


        em.createQuery(criteriaQuery)
                .getResultList()
                .forEach(System.out::println);

        em.getTransaction().commit();
    }

    private void baseJoin() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        //открываем транзакцию и создаём CriteriaBuilder который будет строить объекты запросов
        CriteriaBuilder cb = em.getCriteriaBuilder();

        // параметризуется типом, который этот запрос возвращаем
        CriteriaQuery<Fixture> fixtureCriteria = cb.createQuery(Fixture.class);
        Root<Fixture> fixtureRoot = fixtureCriteria.from(Fixture.class);


        Join<Fixture, Sport> sport = fixtureRoot.join("sport");


        fixtureCriteria.select(fixtureRoot);

        em.createQuery(fixtureCriteria)
                .getResultList()
                .forEach(System.out::println);
    }

    private void basePredicateTest() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Sport> personCriteria = cb.createQuery(Sport.class);
        //корневой объект, от которого производится обход дерева свойств
        Root<Sport> personRoot = personCriteria.from(Sport.class);
        // что мы хотим выиграть

        personCriteria.where(cb.equal(personRoot.get("name"), "Tennis"));
        personCriteria.select(personRoot);


        em.createQuery(personCriteria)
                .getResultList()
                .forEach(System.out::println);
    }
}
