package com.se.sample.repository;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyIterable.emptyIterable;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.IsNot.not;

//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import com.se.sample.UserSpecificationsBuilder;
import com.se.sample.model.User;
import com.se.sample.util.SearchCriteria;
import com.se.sample.util.SearchOperation;
import com.se.sample.util.SpecSearchCriteria;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIn.isIn;
import static org.hamcrest.core.IsNot.not;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { PersistenceJPAConfig.class })
//@Transactional
//@TransactionConfiguration
class JPASpecificationsTest {

    @Autowired
    private UserRepository repository;

    private   User userJohn;
    private  User userTom;


    @BeforeEach
    public   void init() {
        userJohn = new User();
        userJohn.setFirstName("john");
        userJohn.setLastName("doe");
        userJohn.setEmail("john@doe.com");
        userJohn.setAge(22);
        repository.save(userJohn);

        userTom = new User();
        userTom.setFirstName("Tom");
        userTom.setLastName("doe");
        userTom.setEmail("tom@doe.com");
        userTom.setAge(26);
        repository.save(userTom);
    }

    @Test
    @DisplayName("Testing addition functionality")
    public void givenFirstAndLastName_whenGettingListOfUsers_thenCorrect() {
        final UserSpecification spec = new UserSpecification(new SpecSearchCriteria("firstName", SearchOperation.EQUALITY, "john"));
        final UserSpecification spec1 = new UserSpecification(new SpecSearchCriteria("lastName", SearchOperation.EQUALITY, "doe"));
        final List<User> results = repository.findAll(Specification
                .where(spec)
                .and(spec1));

        assertThat(userJohn, isIn(results));
        assertThat(userTom, not(isIn(results)));
    }

    @Test
    public void givenFirstOrLastName_whenGettingListOfUsers_thenCorrect() {
        UserSpecificationsBuilder builder = new UserSpecificationsBuilder();

        SpecSearchCriteria spec = new SpecSearchCriteria("firstName", SearchOperation.EQUALITY, "john");
        SpecSearchCriteria spec1 = new SpecSearchCriteria("'","lastName", SearchOperation.EQUALITY, "doe");

        List<User> results = repository.findAll(builder
                .with(spec)
                .with(spec1)
                .build());

        assertThat(results, hasSize(6));
        assertThat(userJohn, isIn(results));
        assertThat(userTom, isIn(results));
    }


    @Test
    public void givenFirstNameInverse_whenGettingListOfUsers_thenCorrect() {
        final UserSpecification spec = new UserSpecification(new SpecSearchCriteria("firstName", SearchOperation.NEGATION, "john"));
        final List<User> results = repository.findAll(Specification.where(spec));

        assertThat(userTom, isIn(results));
        assertThat(userJohn, not(isIn(results)));
    }

    @Test
    public void givenMinAge_whenGettingListOfUsers_thenCorrect() {
        final UserSpecification spec = new UserSpecification(new SpecSearchCriteria("age", SearchOperation.GREATER_THAN, "25"));
        final List<User> results = repository.findAll(Specification.where(spec));
        assertThat(userTom, isIn(results));
        assertThat(userJohn, not(isIn(results)));
    }

    @Test
    public void givenFirstNamePrefix_whenGettingListOfUsers_thenCorrect() {
        final UserSpecification spec = new UserSpecification(new SpecSearchCriteria("firstName", SearchOperation.STARTS_WITH, "jo"));
        final List<User> results = repository.findAll(spec);
        assertThat(userJohn, isIn(results));
        assertThat(userTom, not(isIn(results)));
    }

    @Test
    public void givenFirstNameSuffix_whenGettingListOfUsers_thenCorrect() {
        final UserSpecification spec = new UserSpecification(new SpecSearchCriteria("firstName", SearchOperation.ENDS_WITH, "n"));
        final List<User> results = repository.findAll(spec);
        assertThat(userJohn, isIn(results));
        assertThat(userTom, not(isIn(results)));
    }

    @Test
    public void givenFirstNameSubstring_whenGettingListOfUsers_thenCorrect() {
        final UserSpecification spec = new UserSpecification(new SpecSearchCriteria("firstName", SearchOperation.CONTAINS, "oh"));
        final List<User> results = repository.findAll(spec);

        assertThat(userJohn, isIn(results));
        assertThat(userTom, not(isIn(results)));
    }

    @Test
    public void givenAgeRange_whenGettingListOfUsers_thenCorrect() {
        final UserSpecification spec = new UserSpecification(new SpecSearchCriteria("age", SearchOperation.GREATER_THAN, "20"));
        final UserSpecification spec1 = new UserSpecification(new SpecSearchCriteria("age", SearchOperation.LESS_THAN, "25"));
        final List<User> results = repository.findAll(Specification
                .where(spec)
                .and(spec1));

        assertThat(userJohn, isIn(results));
        assertThat(userTom, not(isIn(results)));
    }
}