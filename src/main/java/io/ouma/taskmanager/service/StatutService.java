package io.ouma.taskmanager.service;

import java.util.List;

import io.ouma.taskmanager.model.Statut;

public interface StatutService {

	public List<Statut> getStatuts();
	public Statut getStatutById(Long id);
	public void addStatut (Statut statut);
	public void updatedStatut(Statut updatedStatut, Long id);
	public void deleteStatut(Long id);
}
