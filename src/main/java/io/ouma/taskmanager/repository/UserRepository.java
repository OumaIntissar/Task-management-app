package io.ouma.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.ouma.taskmanager.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
