package io.ouma.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ouma.taskmanager.model.Task;
import io.ouma.taskmanager.model.User;
import io.ouma.taskmanager.service.TaskService;
import io.ouma.taskmanager.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	TaskService taskService;

	@ApiOperation(value = "finds all users.")
	@GetMapping
	public List<User> getAllUsers() {
		log.trace("Entred get /api/users endpoint");
		return userService.getUsers();
	}

	@ApiOperation(value = "finds users by ID.")

	@GetMapping(value = "/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		log.trace("Entred get /api/users/id endpoint");
		return userService.getUserById(id);
	}

	@ApiOperation(value = "Add new user.")
	@PostMapping
	public void addUser(@RequestBody User newUser) {
		log.trace("Entred post /api/users endpoint");
		userService.addUser(newUser);
	}

	@ApiOperation(value = "finds tasks by user.")
	@GetMapping("/{userId}/tasks")
	public List<Task> getTasksByUser(@PathVariable("userId") Long userId) {
		log.trace("Entred get /api/users/{id}/tasks endpoint");
		return taskService.getTasksByUser(userId);
	}

	@ApiOperation(value = "Add task by user.")
	@PostMapping("/{userId}/tasks")
	public void addTask(@PathVariable("userId") Long userId, @RequestBody Task newTask) {
		log.trace("Entred post /api/users/{id}/tasks endpoint");
		taskService.addTask(userId, newTask);
	}

	@ApiOperation(value = "Delete user by ID.")
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Long userId) {
		log.trace("Entred delete /api/users/{id} endpoint");
		userService.deleteUser(userId);
	}

	@ApiOperation(value = "Update user by ID.")
	@PutMapping("/{id}")
	public void updateUser(@PathVariable("id") Long userId, @RequestBody User updatedUser) {
		log.trace("Entred put /api/users/{id} endpoint");
		userService.updateUser(updatedUser, userId);
	}
}
