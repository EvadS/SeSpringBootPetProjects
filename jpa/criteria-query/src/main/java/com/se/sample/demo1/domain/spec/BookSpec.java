package com.se.sample.demo1.domain.spec;

import com.se.sample.demo1.domain.MyBook;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


//Using JPA Specifications
public class BookSpec {

    private BookSpec(){}

//    public static Specification<MyBook> hasOwnerName(String authorName, String title ) {
//
//        // Root что пойдет в результат
//        // builder создаем запросы пошагово
//        // criteriaQuery не посредственно условия
//        Specification<MyBook>bookSpec = new Specification<MyBook>() {
//            /**
//             *
//             * @param root the type of the {@link Root} the resulting
//             * @param criteriaQuery reference, which we can use to create different parts of the query
//             * @param criteriaBuilder which describes what we want to do in the query.
//             * @return
//             */
//            @Override
//            public Predicate toPredicate(Root<MyBook> book, CriteriaQuery<?> cq, CriteriaBuilder criteriaBuilder) {
//                //Root<MyBook> book = cq.from(MyBook.class);
//
//                Predicate authorNamePredicate = criteriaBuilder.equal(book.get("author"), authorName);
//                Predicate titlePredicate = criteriaBuilder.like(book.get("title"), "%" + title + "%");
//                cq.where(authorNamePredicate, titlePredicate);
//
//
//                criteriaBuilder.equals(book.get("title"), "title"))
//
////                TypedQuery<MyBook> query = em.createQuery(cq);
////                return query.getResultList();
//            }
//        };
//    }


    static Specification<MyBook> titleContains(String title) {
        return (book, cq, cb) -> cb.like(book.get("title"), "%" + title + "%");
    }


}
