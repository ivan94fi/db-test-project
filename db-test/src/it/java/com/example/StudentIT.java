package com.example;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentIT {

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
