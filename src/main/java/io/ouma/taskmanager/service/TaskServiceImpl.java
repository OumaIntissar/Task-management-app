package io.ouma.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.ouma.taskmanager.exception.StatutNotFoundException;
import io.ouma.taskmanager.exception.TaskNotFoundException;
import io.ouma.taskmanager.exception.UserNotFoundException;
import io.ouma.taskmanager.model.Statut;
import io.ouma.taskmanager.model.Task;
import io.ouma.taskmanager.model.User;
import io.ouma.taskmanager.repository.StatutRepository;
import io.ouma.taskmanager.repository.TaskRepository;
import io.ouma.taskmanager.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepo;

	@Autowired
	UserRepository userRepository;

	@Autowired
	StatutRepository statutRepo;

	@Override
	public List<Task> getTasks() {
		log.trace("Get all tasks succeed");
		return taskRepo.findAll();
	}

	@Override
	public Task getTaskById(Long id) {
		if (taskRepo.findById(id).isPresent()) {
			log.trace("Get task with id = " + id + " succeed");
			return taskRepo.findById(id).get();
		} else {
			throw new TaskNotFoundException(id);
		}
	}

	@Override
	public List<Task> getTasksByUser(Long userId) {
		if (userRepository.findById(userId).isPresent()) {
			log.trace("Get tasks of user " + userId + " succeed");
			return taskRepo.findByUserId(userId);
		} else {
			throw new UserNotFoundException(userId);
		}
	}

	@Override
	public List<Task> getTaskByStatut(Long id) {
		if (statutRepo.findById(id).isPresent()) {
			log.trace("Get tasks " + taskRepo.findByUserId(id) + " with statut " + id + " succeed");
			return taskRepo.findByStatutId(id);
		} else {
			throw new StatutNotFoundException(id);
		}
	}

	@Override
	public void addTask(Long userId, Task task) {
		log.trace("Add Task ........");
		if (userRepository.findById(userId).isPresent()) {
			User user = userRepository.findById(userId).get();
			task.setUser(user);
			log.trace("Affecting user with id = " + userId + " to new task '" + task.getName() + "' succeed");
		} else {
			throw new UserNotFoundException(userId);
		}

		// Default statut
		if (statutRepo.findByName("Not started") == null) {
			log.trace("Default statut doesn't exist");
			Statut newStatut = new Statut();
			newStatut.setName("Not started");
			statutRepo.save(newStatut);
			log.trace("Creating default statut with name {Not started} succeed");
			task.setStatut(newStatut);
			log.trace("Affecting default statut to new task " + task + " succeed");
		} else {
			Statut statut = statutRepo.findByName("Not started");
			task.setStatut(statut);
			log.trace("Affecting default statut to new task " + task + " succeed");
		}

		taskRepo.save(task);
		log.trace("Add new task " + task + " to user with id = " + userId + " succeed");

	}

	@Override
	public void updateTask(Task updatedTask, Long idTaskToUp) {
		// Check idTask exists
		if (taskRepo.findById(idTaskToUp).isPresent()) {
			Task task = taskRepo.findById(idTaskToUp).get();
			log.trace("Updating existing task");
			if (updatedTask.getName() != null)
				task.setName(updatedTask.getName());
			if (updatedTask.getStatut() != null)
				task.setStatut(updatedTask.getStatut());
			if (updatedTask.getToDoDateTime() != null)
				task.setToDoDateTime(updatedTask.getToDoDateTime());
			if (updatedTask.getUser() != null)
				task.setUser(updatedTask.getUser());

			taskRepo.save(task);
			log.trace("Update task succeed");
		} else {
			throw new TaskNotFoundException(idTaskToUp);
		}

	}

	@Override
	public void deleteTask(Long id) {

		if (taskRepo.findById(id).isPresent()) {
			taskRepo.deleteById(id);
			log.trace("Delete task with id = " + id + " succeed ");
		} else {
			throw new TaskNotFoundException(id);
		}
	}

	@Override
	public void updateTaskStatut(Long statutId, Long idTaskToUp) {
		// Check idTask exists
		if (taskRepo.findById(idTaskToUp).isPresent()) {
			log.trace("Updating Statut for existing task");
			Task task = taskRepo.findById(idTaskToUp).get();
			Statut statut = statutRepo.findById(statutId).get();
			
			task.setStatut(statut);

			taskRepo.save(task);
			log.trace("Update statut for task succeed");
		} else {
			throw new TaskNotFoundException(idTaskToUp);
		}

	}

}
