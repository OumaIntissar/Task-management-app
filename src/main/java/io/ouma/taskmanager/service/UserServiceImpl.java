package io.ouma.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.ouma.taskmanager.exception.UserNotFoundException;
import io.ouma.taskmanager.model.User;
import io.ouma.taskmanager.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Override
	public List<User> getUsers() {
		log.trace("Get all users succeed");
		return userRepo.findAll();
	}

	@Override
	public User getUserById(Long id) {

		if (userRepo.findById(id).isPresent()) {
			log.trace("Getting user with id = " + id + " succeed");
			return userRepo.findById(id).get();
		} else {
			UserNotFoundException e = new UserNotFoundException(id);
			throw e;
		}

	}

	@Override
	public void addUser(User user) {
		userRepo.save(user);
		log.trace("Add new user = " + user + " succeed");
	}

	@Override
	public void updateUser(User updatedUser, Long idUserToUp) {

		if (userRepo.findById(idUserToUp).isPresent()) {

			User user = userRepo.findById(idUserToUp).get();

			log.trace("Updating user informations from  " + user + " to " + updatedUser + " ...");

			if (updatedUser.getEmail() != null)
				user.setEmail(updatedUser.getEmail());

			if (updatedUser.getFirstName() != null)
				user.setFirstName(updatedUser.getFirstName());

			if (updatedUser.getLastName() != null)
				user.setLastName(updatedUser.getLastName());

			if (updatedUser.getTasks() != null)
				user.setTasks(updatedUser.getTasks());

			userRepo.save(user);
			log.trace("Update succeed");

		} else {
			throw new UserNotFoundException(idUserToUp);
		}

	}

	@Override
	public void deleteUser(Long id) {

		if (userRepo.findById(id).isPresent()) {
			userRepo.deleteById(id);
			log.trace("Delete user with id = " + id + " succeed");
		} else {
			throw new UserNotFoundException(id);
		}

	}

}
