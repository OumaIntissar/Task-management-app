package io.ouma.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ouma.taskmanager.exception.StatutNotFoundException;
import io.ouma.taskmanager.model.Statut;
import io.ouma.taskmanager.repository.StatutRepository;

@Service
public class StatutServiceImpl implements StatutService {

	@Autowired
	StatutRepository statutRepo;

	@Override
	public List<Statut> getStatuts() {
		return statutRepo.findAll();
	}

	@Override
	public Statut getStatutById(Long id) {
		if (statutRepo.findById(id).isPresent())
			return statutRepo.findById(id).get();
		else {
			throw new StatutNotFoundException(id);
		}
	}

	@Override
	public void addStatut(Statut statut) {
		statutRepo.save(statut);

	}

	@Override
	public void updatedStatut(Statut updatedStatut, Long id) {

		if (statutRepo.findById(id).isPresent()) {

			Statut statut = statutRepo.findById(id).get();

			if (updatedStatut.getName() != null)
				statut.setName(updatedStatut.getName());
			if (updatedStatut.getTasks() != null)
				statut.setTasks(updatedStatut.getTasks());

			statutRepo.save(statut);

		} else {
			throw new StatutNotFoundException(id);
		}

	}

	@Override
	public void deleteStatut(Long id) {
		
		if (statutRepo.findById(id).isPresent()) {
			statutRepo.deleteById(id);
		} else {
			throw new StatutNotFoundException(id);
		}

	}

}
