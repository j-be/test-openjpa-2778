package org.duckdns.owly.bugs.openjpa_2778.service;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.duckdns.owly.bugs.openjpa_2778.entities.SomeEntity;

@Repository
public interface SomeRepository extends EntityRepository<SomeEntity, Long> {
}
