package io.ouma.taskmanager.repository;

import io.ouma.taskmanager.model.Statut;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class StatutRepositoryTest {

    @Autowired
    private StatutRepository statutRepository;
    private Statut underTest;

    @BeforeEach
    void setUp() {
        underTest = new Statut();
    }

    @AfterEach
    void tearDown() {
        statutRepository.deleteAll();
    }

    @Test
    void shouldFindStatutByName() {

        underTest.setName("Open");
        statutRepository.save(underTest);

        assertThat(underTest).isEqualTo(statutRepository.findByName("Open"));

    }
}