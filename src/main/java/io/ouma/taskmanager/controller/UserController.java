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

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	TaskService taskService;

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getUsers();
	}
	
	@GetMapping(value = "/{id}")
	public User getUserById(@PathVariable(name = "id") Long id) {
		//System.out.println( id);
		return userService.getUserById(id);
	}

	@PostMapping
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}
	
	@GetMapping("/{userId}/tasks")
	public List<Task> getTasksByUser(@PathVariable (name="userId") Long userId){
		return taskService.getTasksByUser(userId);
	}
	
	@PostMapping("/{userId}/tasks")
	public void addTask(@PathVariable (name="userId") Long userId, @RequestBody Task task){
		taskService.addTask(task, userId);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}

	@PutMapping("/{id}")
	public void updateUser(@PathVariable Long id, @RequestBody User user) {
		userService.updateUser(user, id);
	}
}
