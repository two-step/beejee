package com.beejee.task.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.beejee.task.model.Task;

public interface ITaskRepository extends CrudRepository<Task, UUID> {

}
