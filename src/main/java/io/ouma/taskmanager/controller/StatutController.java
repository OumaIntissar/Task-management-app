package io.ouma.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.ouma.taskmanager.model.Statut;
import io.ouma.taskmanager.model.Task;
import io.ouma.taskmanager.service.StatutService;
import io.ouma.taskmanager.service.TaskService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/status")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200/")
public class StatutController {

	@Autowired
	StatutService statutService;
	
	@Autowired
	TaskService taskService;
	
	@ApiOperation(value = "Finds all status.")
	@GetMapping
	public List<Statut> getAllSatuts() {
		log.trace("Entred get /api/status endpoint");
		return statutService.getStatuts();
	}

	@ApiOperation(value = "Finds status by id.")
	@GetMapping("/{id}")
	public Statut getSTatutById(@PathVariable("id") Long statutId) {
		log.trace("Entred get /api/status/id endpoint");
		return statutService.getStatutById(statutId);
	}

	@ApiOperation(value = "Finds tasks by statut id.")
	@GetMapping("/{id}/tasks")
	public List<Task> getTasksBySatut(@PathVariable("id") Long statutId) {
		log.trace("Entred get /api/status/{id}/tasks endpoint");
		return taskService.getTaskByStatut(statutId);
	}

	@ApiOperation(value = "Add new statut.")
	@PostMapping
	public void addStatut(@RequestBody Statut newStatut) {
		log.trace("Entred post /api/status endpoint");
		statutService.addStatut(newStatut);
	}
	
	@ApiOperation(value = "Delete statut by id.")
	@DeleteMapping("/{id}")
	public void deleteStatut(@PathVariable("id") Long statutId) {
		log.trace("Entred delete /api/status/{id} endpoint");
		statutService.deleteStatut(statutId);
	}
	
	@ApiOperation(value = "Update existed statut.")
	@PutMapping("/{id}")
	public void updateStatut(@PathVariable("id") Long statutId, @RequestBody Statut updatedStatut) {
		log.trace("Entred put /api/status/{id} endpoint");
		statutService.updatedStatut(updatedStatut, statutId);
	}

}
