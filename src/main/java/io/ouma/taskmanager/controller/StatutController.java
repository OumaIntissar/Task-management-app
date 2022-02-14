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

@RestController
@RequestMapping(value = "/status")
public class StatutController {

	@Autowired
	StatutService statutService;
	
	@Autowired
	TaskService taskService;

	@GetMapping
	public List<Statut> getAllSatuts() {
		return statutService.getStatuts();
	}

	@GetMapping("/{id}")
	public Statut getSTatutById(@PathVariable Long id) {
		return statutService.getStatutById(id);
	}

	
	@GetMapping("/{id}/tasks")
	public List<Task> getTasksBySatut(@PathVariable Long id) {
		return taskService.getTaskByStatut(id);
	}

	@PostMapping
	public void addStatut(@RequestBody Statut newStatut) {
		statutService.addStatut(newStatut);
	}
	
	@DeleteMapping("/{id}")
	public void deleteStatut(@PathVariable Long id) {
		statutService.deleteStatut(id);
	}
	
	@PutMapping("/{id}")
	public void updateStatut(@PathVariable Long id, @RequestBody Statut statut) {
		statutService.updatedStatut(statut, id);
	}

}
