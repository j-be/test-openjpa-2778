package org.duckdns.owly.bugs.test.openjpa_2778;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.duckdns.owly.bugs.openjpa_2778.entities.SomeEntity;
import org.duckdns.owly.bugs.openjpa_2778.service.SomeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
public class OpenJpa2778Test {

    @Inject
    private SomeRepository someRepository;

    @Test
    public void testReproduceTheBugByInsert() {
        // Create it
        SomeEntity someEntity = new SomeEntity();
        someEntity.setNonLobValue(getString(1001, 'a'));
        someEntity.setLobValue(getString(32000, 'a'));
        someRepository.saveAndFlush(someEntity);
    }

    @Test
    public void testReproduceTheBugByUpdate() {
        // Create it
        SomeEntity someEntity = new SomeEntity();
        someEntity.setNonLobValue("short");
        someEntity.setLobValue(getString(32000, 'a'));
        someEntity = someRepository.saveAndFlushAndRefresh(someEntity);

        // Update it
        someEntity.setNonLobValue(getString(1001, 'b'));
        someEntity.setLobValue(getString(32000, 'b'));
        someRepository.saveAndFlush(someEntity);
    }

    private String getString(int length, char character) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++)
            sb.append(character);
        return sb.toString();
    }
}
