package com.example;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {

    private EntityManagerFactory entityManagerFactory;
    private static final Logger LOGGER = LogManager
            .getLogger(StudentTest.class);

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence
                .createEntityManagerFactory("com.example.h2_unit");

    }

    @After
    public void tearDown() throws Exception {
        entityManagerFactory.close();
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
        entityManager.getTransaction().begin();
        entityManager.persist(studentA);
        entityManager.persist(studentB);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        List<Student> result = entityManager
                .createQuery("from Student", Student.class).getResultList();
        for (Student stud : result) {
            LOGGER.info("Student(" + stud.getFirstName() + ", "
                    + stud.getLastName() + ")");
        }
        entityManager.getTransaction().commit();

        entityManagerFactory.close();

        assertTrue(true);

    }

}
