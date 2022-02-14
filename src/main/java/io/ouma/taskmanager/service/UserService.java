package io.ouma.taskmanager.service;

import java.util.List;

import io.ouma.taskmanager.model.User;

public interface UserService {

	public List<User> getUsers();
	public User getUserById(Long id);
	public void addUser(User user);
	public void updateUser(User updatedUser, Long idUserToUp);
	public void deleteUser(Long id);
	
	
}
