package org.duckdns.owly.bugs.openjpa_2778.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("SomeUnit");

    @Produces
    @RequestScoped
    public EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    public void dispose(@Disposes final EntityManager entityManager) {
        entityManager.close();
    }
}
