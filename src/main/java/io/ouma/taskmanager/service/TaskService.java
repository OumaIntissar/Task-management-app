package io.ouma.taskmanager.service;

import java.util.List;

import io.ouma.taskmanager.model.Task;

public interface TaskService {
	public List<Task> getTasks();

	public Task getTaskById(Long id);

	public List<Task> getTasksByUser(Long userId);

	public List<Task> getTaskByStatut(Long id);

	public void addTask(Long userId, Task task);

	public void updateTask(Task updatedTask, Long idTaskToUp);

	public void updateTaskStatut(Long statutId, Long idTaskToUp);

	public void deleteTask(Long id);
}
