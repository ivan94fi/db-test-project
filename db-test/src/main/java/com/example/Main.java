package com.example;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        LOGGER.info("MAGI FROM LOGGER");
        LOGGER.debug("MAGI FROM LOGGER DEBUG");
        Greeter gr = new Greeter();
        String str = "main";
        gr.method1(str);

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("com.example");

        EntityManager entityManager = entityManagerFactory
                .createEntityManager();

        Student student = new Student("simo", "magi");

        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        List<Student> result = entityManager
                .createQuery("from Student", Student.class).getResultList();
        for (Student stud : result) {
            System.out.println("Student(" + stud.getFirstName() + ", "
                    + stud.getLastName() + ")");
        }
        entityManager.getTransaction().commit();

        entityManagerFactory.close();

    }
}
