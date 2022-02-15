package io.ouma.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ouma.taskmanager.exception.UserNotFoundException;
import io.ouma.taskmanager.model.User;
import io.ouma.taskmanager.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Override
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	@Override
	public User getUserById(Long id) {

		if (userRepo.findById(id).isPresent()) {
			return userRepo.findById(id).get();
		} else {
			throw new UserNotFoundException(id);
		}

	}

	@Override
	public void addUser(User user) {
		userRepo.save(user);
	}

	@Override
	public void updateUser(User updatedUser, Long idUserToUp) {

		if (userRepo.findById(idUserToUp).isPresent()) {

			User user = userRepo.findById(idUserToUp).get();

			if (updatedUser.getEmail() != null)
				user.setEmail(updatedUser.getEmail());

			if (updatedUser.getFirstName() != null)
				user.setFirstName(updatedUser.getFirstName());

			if (updatedUser.getLastName() != null)
				user.setLastName(updatedUser.getLastName());

			if (updatedUser.getTask() != null)
				user.setTask(updatedUser.getTask());

			userRepo.save(user);

		} else {
			throw new UserNotFoundException(idUserToUp);
		}

	}

	@Override
	public void deleteUser(Long id) {

		if (userRepo.findById(id).isPresent()) {
			userRepo.deleteById(id);
		} else {
			throw new UserNotFoundException(id);
		}

	}

}
