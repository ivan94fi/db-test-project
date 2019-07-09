package com.example;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StudentIT {

    private static EntityManagerFactory entityManagerFactory;
    private static final Logger LOGGER = LogManager
            .getLogger(StudentTest.class);

    private static void doInTransaction(
            final Consumer<EntityManager> consumer) {
        final EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (final Exception exception) {
            em.getTransaction().rollback();
            throw exception;
        }
    }

    @BeforeClass
    public static void setUp() throws Exception {
        entityManagerFactory = Persistence
                .createEntityManagerFactory("com.example.postgres_unit");

    }

    @AfterClass
    public static void tearDown() throws Exception {
        entityManagerFactory.close();
    }

    @Before
    public void before() {
        doInTransaction(em -> {
            em.createQuery("DELETE FROM Student").executeUpdate();
        });
    }

    @Test
    public void testExample() {
        EntityManager entityManager = entityManagerFactory
                .createEntityManager();

        final String studentAFirstName = "AAA";
        final String studentALastName = "AAA";
        final String studentBFirstName = "BBB";
        final String studentBLastName = "BBB";

        Student studentA = new Student(studentAFirstName, studentALastName);
        Student studentB = new Student(studentBFirstName, studentBLastName);
        final List<Student> students = Arrays.asList(studentA, studentB);
        entityManager.getTransaction().begin();
        for (Student stud : students) {
            entityManager.persist(stud);
        }
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        List<Student> result = entityManager
                .createQuery("from Student", Student.class).getResultList();

        for (int i = 0; i < result.size(); i++) {
            Student stud = result.get(i);
            LOGGER.info("Student(" + stud.getFirstName() + ", "
                    + stud.getLastName() + ")");
            assertEquals(stud, students.get(i));
        }
        entityManager.getTransaction().commit();

        entityManagerFactory.close();

    }

}
