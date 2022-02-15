package io.ouma.taskmanager.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ouma.taskmanager.model.Statut;
import io.ouma.taskmanager.model.Task;
import io.ouma.taskmanager.model.User;
import io.ouma.taskmanager.repository.StatutRepository;
import io.ouma.taskmanager.repository.TaskRepository;
import io.ouma.taskmanager.repository.UserRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepo;

	@Autowired
	UserRepository userRepository;

	@Autowired
	StatutRepository statutRepo;

	@Override
	public List<Task> getTasks() {
		return taskRepo.findAll();
	}

	@Override
	public Task getTaskById(Long id) {
		if (taskRepo.findById(id).isPresent()) {
			return taskRepo.findById(id).get();
		} else {
			throw new NoSuchElementException("Cannot find task with id = " + id);
		}
	}

	@Override
	public List<Task> getTasksByUser(Long userId) {
		if (userRepository.findById(userId).isPresent()) {
			return taskRepo.findByUserId(userId);
		} else {
			throw new NoSuchElementException("Cannot find user with id = " + userId);
		}
	}

	@Override
	public List<Task> getTaskByStatut(Long id) {
		if (statutRepo.findById(id).isPresent()) {
		return taskRepo.findByStatutId(id);
	} else {
		throw new NoSuchElementException("Cannot find statut with id = " + id);
	}
	}

	@Override
	public void addTask(Long userId, Task task) {

		if (userRepository.findById(userId).isPresent()) {
			User user = userRepository.findById(userId).get();
			task.setUser(user);
		} else {
			throw new NoSuchElementException("Cannot find user with id = " + userId);
		}

		// Default statut
		if (statutRepo.findByName("No Statut") == null) {
			Statut newStatut = new Statut();
			newStatut.setName("No Statut");
			statutRepo.save(newStatut);
			task.setStatut(newStatut);
		} else {
			Statut statut = statutRepo.findByName("No Statut");
			task.setStatut(statut);
		}

		taskRepo.save(task);

	}

	@Override
	public void updateTask(Task updatedTask, Long idTaskToUp) {
		// Check idTask exists
		if (taskRepo.findById(idTaskToUp).isPresent()) {
			Task task = taskRepo.findById(idTaskToUp).get();
			if (updatedTask.getName() != null)
				task.setName(updatedTask.getName());
			if (updatedTask.getStatut() != null)
				task.setStatut(updatedTask.getStatut());
			if (updatedTask.getToDoDateTime() != null)
				task.setToDoDateTime(updatedTask.getToDoDateTime());
			if (updatedTask.getUser() != null)
				task.setUser(updatedTask.getUser());

			taskRepo.save(task);
		} else {
			throw new NoSuchElementException("Cannot find task with id = " + idTaskToUp);
		}

	}

	@Override
	public void deleteTask(Long id) {

		if (taskRepo.findById(id).isPresent()) {
			taskRepo.deleteById(id);
		} else {
			throw new NoSuchElementException("Cannot find task with id = " + id);
		}
	}

}
