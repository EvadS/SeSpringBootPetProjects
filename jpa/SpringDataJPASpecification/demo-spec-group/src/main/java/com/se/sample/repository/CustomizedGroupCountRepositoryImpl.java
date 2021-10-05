package com.se.sample.repository;

import com.se.sample.model.AlarmData;
import com.se.sample.model.AlarmMsg;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class CustomizedGroupCountRepositoryImpl implements CustomizedGroupCountRepository {
    private final EntityManager entityManager;


    public CustomizedGroupCountRepositoryImpl(EntityManager entityManager) {
        Assert.notNull(entityManager, "EntityManager must not be null!");
        this.entityManager = entityManager;
    }

    @Override
    public Map<AlarmMsg.AlarmLevel, Long> groupAndCount(SingularAttribute singularAttribute, Specification where) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Tuple> query = criteriaBuilder.createQuery(Tuple.class);
        final Root<AlarmData> root = query.from(AlarmData.class);
        final Path<AlarmMsg.AlarmLevel> expression = root.get(singularAttribute);
        query.multiselect(expression, criteriaBuilder.count(root));
        query.select(criteriaBuilder.tuple(expression, criteriaBuilder.count(root)));
        query.where(where.toPredicate(root, query, criteriaBuilder));
        query.groupBy(expression);
        final List<Tuple> resultList = entityManager.createQuery(query).getResultList();
        return resultList.stream()
                .collect(toMap(
                        t -> t.get(0, AlarmMsg.AlarmLevel.class),
                        t -> t.get(1, Long.class))
                );
    }
}

