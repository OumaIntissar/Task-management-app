package io.ouma.taskmanager.repository;

import io.ouma.taskmanager.model.Statut;
import io.ouma.taskmanager.model.Task;
import io.ouma.taskmanager.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatutRepository statutRepository;

    List<Task> tasks;
    User user;
    Statut statut;
    Task task;
    Task task1;
    List<Task> expectedTasks;

    @BeforeEach
    void setUp() {
        tasks = new ArrayList<>();
        expectedTasks = new ArrayList<>();
        user = new User();
        statut = new Statut();
        task = new Task();
        task1 = new Task();
    }

    @AfterEach
    void tearDown() {
        taskRepository.deleteAll();
        userRepository.deleteAll();
        statutRepository.deleteAll();
    }

    @Test
    void shouldFindTasksByUserId() {

        user.setFirstName("Oumaiyma");
        user.setLastName("Intissar");
        user.setEmail("ouma@gmail.com");
        userRepository.save(user);

        statut.setName("statut");
        statutRepository.save(statut);

        task.setName("task");
        task.setUser(user);
        task.setStatut(statut);
        tasks.add(task);
        taskRepository.save(task);

        task1.setName("task 1");
        task1.setUser(user);
        task1.setStatut(statut);
        tasks.add(task1);
        taskRepository.save(task1);

        user.setTasks(tasks);
        statut.setTasks(tasks);

        expectedTasks = taskRepository.findByUserId(user.getId());
        assertThat(expectedTasks).isEqualTo(tasks);

    }

    @Test
    void ShouldFindTasksByStatutId() {

        user.setFirstName("Oumaiyma");
        user.setLastName("Intissar");
        user.setEmail("ouma@gmail.com");
        userRepository.save(user);

        statut.setName("statut");
        statutRepository.save(statut);

        task.setName("task");
        task.setUser(user);
        task.setStatut(statut);
        tasks.add(task);
        taskRepository.save(task);

        task1.setName("task 1");
        task1.setUser(user);
        task1.setStatut(statut);
        tasks.add(task1);
        taskRepository.save(task1);

        user.setTasks(tasks);
        statut.setTasks(tasks);

        expectedTasks = taskRepository.findByStatutId(statut.getId());
        assertThat(expectedTasks).isEqualTo(tasks);

    }
}