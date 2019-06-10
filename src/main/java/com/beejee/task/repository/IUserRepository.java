package com.beejee.task.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.beejee.task.model.User;

public interface IUserRepository extends CrudRepository<User, UUID> {
	User findByLoginAndPassword(String login, String psw);
}
