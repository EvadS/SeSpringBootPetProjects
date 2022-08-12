package com.se.one.to.one;

import com.se.one.to.one.entity.CompositeTaskId;
import com.se.one.to.one.entity.Employee;
import com.se.one.to.one.entity.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * hibernate-core  demo
 */
public class ExampleMain {

    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("example-unit");
        try {
            EntityManager em = emf.createEntityManager();
            nativeQuery(em, "SHOW TABLES");
            nativeQuery(em, "SHOW COLUMNS from TASK");
            nativeQuery(em, "SHOW COLUMNS from EMPLOYEE");



            persistEntity(emf);
            runNativeQuery(emf);
            findEntityById(emf);

        } finally {
            emf.close();
        }
    }

    private static void runNativeQuery(EntityManagerFactory emf) {
        System.out.println("-- Native query --");
        EntityManager em = emf.createEntityManager();
        ExampleMain.nativeQuery(em, "Select * from EMPLOYEE");
        ExampleMain.nativeQuery(em, "Select * from Task");
    }

    private static void persistEntity(EntityManagerFactory emf) throws Exception {
        System.out.println("-- Persisting entity --");
        EntityManager em = emf.createEntityManager();

        Employee e = new Employee(1L, "Mike", "IT");
        CompositeTaskId cti = new CompositeTaskId(1L, 100L);
        Task task = new Task(cti, e);
        task.setTaskName("coding");

        em.getTransaction().begin();
        em.persist(e);
        em.persist(task);
        em.getTransaction().commit();
        em.close();
    }

    private static void findEntityById(EntityManagerFactory emf) {
        System.out.println("-- Finding entity --");
        EntityManager em = emf.createEntityManager();
        CompositeTaskId taskId = new CompositeTaskId(1, 100);
        Task task = em.find(Task.class, taskId);
        System.out.println(task);
        em.close();
    }

    public static void nativeQuery(EntityManager em, String s) {
        System.out.printf("*****************************************************");
        System.out.printf("'%s'%n", s);
        Query query = em.createNativeQuery(s);
        List list = query.getResultList();
        for (Object o : list) {
            if (o instanceof Object[]) {
                System.out.println(Arrays.toString((Object[]) o));
            } else {
                System.out.println(o);
            }
        }
    }
}