package com.se.many.to.many.demo3;

import com.se.many.to.many.demo3.entity.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@SpringBootTest
class FirstLevelCacheTest {
    //TODO: temporary solution
    long cityId;
    @PersistenceContext
    private EntityManager em;
    // В результате такого теста выполнится только один SQL-select:

    //  @BeforeAll /* я пока не знаю что с этим делать */
    public void init() {
        City city1 = new City();
        city1.setName("city1");
        em.persist(city1);

        cityId = city1.getId();

        City city2 = new City();
        city2.setName("city2");
        em.persist(city2);

        City city3 = new City();
        city3.setName("city3");
        em.persist(city3);

        City city4 = new City();
        city4.setName("city4");
        em.persist(city4);
    }


    @Test
    @Transactional/*в начале и в конце теста неявно выполняется begin() и commit() транзакци*/
    public void when2Finds_thenOnefind() {

        init();

        System.out.println("*** BEFORE  find >>>>");
        City city = em.find(City.class, cityId);
        System.out.println("find1");
        Assertions.assertEquals("city1", city.getName());
        /*  выполняться не будет – Hibernate возьмет сущность из кэша первого уровня. */
        System.out.println("*** BEFORE  find >>>>");
        City city2 = em.find(City.class, cityId);
        System.out.println("find2");
        Assertions.assertEquals("city1", city.getName());
    }

}
