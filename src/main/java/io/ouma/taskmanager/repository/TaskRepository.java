package io.ouma.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.ouma.taskmanager.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	public List<Task> findByUserId(Long id);

	public List<Task> findByStatutId(Long id);

}
