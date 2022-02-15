package io.ouma.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ouma.taskmanager.exception.StatutNotFoundException;
import io.ouma.taskmanager.model.Statut;
import io.ouma.taskmanager.repository.StatutRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StatutServiceImpl implements StatutService {

	@Autowired
	StatutRepository statutRepo;

	@Override
	public List<Statut> getStatuts() {
		log.trace("Getting all status");
		return statutRepo.findAll();
	}

	@Override
	public Statut getStatutById(Long id) {
		if (statutRepo.findById(id).isPresent()) {
			log.trace("Getting "+statutRepo.findById(id).get() );
			return statutRepo.findById(id).get();
		}else {
			throw new StatutNotFoundException(id);
		}
	}

	@Override
	public void addStatut(Statut statut) {
		
		statutRepo.save(statut);
		log.trace("Adding new " + statut + " succeed");
	}

	@Override
	public void updatedStatut(Statut updatedStatut, Long id) {

		if (statutRepo.findById(id).isPresent()) {

			Statut statut = statutRepo.findById(id).get();
			log.trace("Updating statut from "+statut+" to "+ updatedStatut);
			if (updatedStatut.getName() != null)
				statut.setName(updatedStatut.getName());
			if (updatedStatut.getTasks() != null)
				statut.setTasks(updatedStatut.getTasks());

			statutRepo.save(statut);
			log.trace("Update succeed");

		} else {
			throw new StatutNotFoundException(id);
		}

	}

	@Override
	public void deleteStatut(Long id) {
		
		if (statutRepo.findById(id).isPresent()) {
			statutRepo.deleteById(id);
			log.trace("Delete statut with "+id+" succeed");
		} else {
			throw new StatutNotFoundException(id);
		}

	}

}
