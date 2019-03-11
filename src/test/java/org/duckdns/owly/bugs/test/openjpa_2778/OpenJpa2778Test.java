package org.duckdns.owly.bugs.test.openjpa_2778;

import org.duckdns.owly.bugs.openjpa_2778.entities.SomeEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OpenJpa2778Test {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("SomeUnit");

    private EntityManager entityManager;

    @Before
    public void createEntityManager() {
        entityManager = emf.createEntityManager();
    }

    @After
    public void closeEntityManager() {
        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().rollback();
        entityManager.close();
    }

    @Test
    public void testReproduceTheBugByInsert() {
        // Create it
        entityManager.getTransaction().begin();
        SomeEntity someEntity = new SomeEntity();
        someEntity.setNonLobValue(getString(1001, 'a'));
        someEntity.setLobValue(getString(32000, 'a'));
        entityManager.persist(someEntity);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    @Test
    public void testReproduceTheBugByUpdate() {
        // Create it
        entityManager.getTransaction().begin();
        SomeEntity someEntity = new SomeEntity();
        someEntity.setNonLobValue("short");
        someEntity.setLobValue(getString(32000, 'a'));
        entityManager.persist(someEntity);
        entityManager.flush();
        entityManager.getTransaction().commit();

        // Update it
        entityManager.getTransaction().begin();
        someEntity.setNonLobValue(getString(1001, 'b'));
        someEntity.setLobValue(getString(32000, 'b'));
        entityManager.merge(someEntity);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    private String getString(int length, char character) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++)
            sb.append(character);
        return sb.toString();
    }
}
