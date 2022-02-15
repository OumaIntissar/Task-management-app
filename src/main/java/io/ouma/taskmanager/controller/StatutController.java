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

import io.ouma.taskmanager.model.Statut;
import io.ouma.taskmanager.model.Task;
import io.ouma.taskmanager.service.StatutService;
import io.ouma.taskmanager.service.TaskService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/status")
public class StatutController {

	@Autowired
	StatutService statutService;
	
	@Autowired
	TaskService taskService;

	@ApiOperation(value = "Finds all status.")
	@GetMapping
	public List<Statut> getAllSatuts() {
		return statutService.getStatuts();
	}

	@ApiOperation(value = "Finds status by id.")
	@GetMapping("/{id}")
	public Statut getSTatutById(@PathVariable("id") Long statutId) {
		return statutService.getStatutById(statutId);
	}

	@ApiOperation(value = "Finds tasks by statut id.")
	@GetMapping("/{id}/tasks")
	public List<Task> getTasksBySatut(@PathVariable("id") Long statutId) {
		return taskService.getTaskByStatut(statutId);
	}

	@ApiOperation(value = "Add new statut.")
	@PostMapping
	public void addStatut(@RequestBody Statut newStatut) {
		statutService.addStatut(newStatut);
	}
	
	@ApiOperation(value = "Delete statut by id.")
	@DeleteMapping("/{id}")
	public void deleteStatut(@PathVariable("id") Long statutId) {
		statutService.deleteStatut(statutId);
	}
	
	@ApiOperation(value = "Update existed statut.")
	@PutMapping("/{id}")
	public void updateStatut(@PathVariable("id") Long statutId, @RequestBody Statut updatedStatut) {
		statutService.updatedStatut(updatedStatut, statutId);
	}

}
