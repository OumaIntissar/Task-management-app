package io.ouma.taskmanager.service;

import io.ouma.taskmanager.model.Statut;
import io.ouma.taskmanager.repository.StatutRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class StatutServiceImplTest {


    @Mock private StatutRepository statutRepository;
    @InjectMocks
    private StatutServiceImpl underTest;



    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getStatuts() {

        List<Statut> statuts = new ArrayList<>();
        statuts.add(new Statut());
        statutRepository.saveAll(statuts);
        given(statutRepository.findAll()).willReturn(statuts);

        List<Statut> expected = underTest.getStatuts();

        assertEquals(expected, statuts);
        verify(statutRepository).findAll();


       /*underTest.getStatuts();
        // then
        verify(statutRepository).findAll();*/
    }

    @Test
    void getStatutById() {
    }

    @Test
    void addStatut() {
    }

    @Test
    void updatedStatut() {
    }

    @Test
    void deleteStatut() {
    }
}