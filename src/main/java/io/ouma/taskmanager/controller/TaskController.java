package io.ouma.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ouma.taskmanager.model.Task;
import io.ouma.taskmanager.service.TaskService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	TaskService taskService;

	@ApiOperation(value = "finds all tasks.")
	@GetMapping
	public List<Task> getAllTasks() {
		return taskService.getTasks();
	}
	
	@ApiOperation(value = "finds tasks by ID.")
	@GetMapping("/{id}")
	public Task getTaskById(@PathVariable("id") Long taskId) {
		return taskService.getTaskById(taskId);
	}
	
	@ApiOperation(value = "Delete task by ID.")
	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable("id") Long taskId) {
		taskService.deleteTask(taskId);
	}
	
	@ApiOperation(value = "Update existed task.")
	@PutMapping("/{id}")
	public void updateTask(@PathVariable("id") Long taskId, @RequestBody Task updatedTask) {
		taskService.updateTask(updatedTask, taskId);
	}
}
