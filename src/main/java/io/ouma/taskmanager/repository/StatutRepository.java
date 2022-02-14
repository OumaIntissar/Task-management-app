package io.ouma.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.ouma.taskmanager.model.Statut;

@Repository
public interface StatutRepository extends JpaRepository<Statut, Long> {

	public Statut findByName(String name);
}
