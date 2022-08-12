package com.se.spec.specification;

import com.se.spec.domain.Note;
import com.se.spec.dto.NoteRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class NoteSpecification extends BaseSpecification<Note, NoteRequest> {
    @Override
    public Specification<Note> getFilter(NoteRequest request) {
        return (root, query, cb) -> {
            query.distinct(true);
            return where(
                    createdDateGreater(request.startedPeriod)
                             .and(endDateLess(request.endPeriod))
            )
                    .toPredicate(root, query, cb);
        };
    }

    private Specification<Note> createdDateGreater(long startedPeriod) {
        Date startedPeriodDate = new Date(startedPeriod);
//        // TODO: for test
//        long timestamp1 = new GregorianCalendar(2000, Calendar.JANUARY, 1,
//                23, 15, 45).getTimeInMillis();
//        Date date1 = new Date(timestamp1);

        Date startDate = new Date(startedPeriod);
        System.out.println("Spec Date start:   " + startDate);

        return noteAttributeGreaterThanOrEqualTo("publishingDate", startDate);
    }

    private Specification<Note> endDateLess(long endPeriod) {
        Date endPeriodDate = new Date(endPeriod);
        System.out.println("Spec Date end:   " + endPeriodDate);

        return dateLessThanOrEqualTo("publishingDate", endPeriodDate);
    }

    private Specification<Note> noteAttributeGreaterThanOrEqualTo(String attribute, Date value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }



            return cb.greaterThanOrEqualTo(root.get(attribute), value);
        };
    }

    private Specification<Note> dateLessThanOrEqualTo(String attribute, Date value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }
            return cb.lessThanOrEqualTo(root.get(attribute), value);
        };
    }

    public Specification<Note> calledBetween(String attribute, LocalDateTime start, LocalDateTime end) {
        return (root, query, cb) ->{
           return  cb.between(root.get(attribute), start, end);
        };
    }
}
