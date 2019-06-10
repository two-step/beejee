package com.beejee.task.service;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beejee.task.model.Task;
import com.beejee.task.repository.ITaskRepository;
import com.beejee.task.util.ImageResizer;

@Service
public class TaskService {
	@Autowired
	private ITaskRepository taskRepo;
	
	public Collection<Task> getAllTasks(){
		return (Collection<Task>)taskRepo.findAll();
	}
	
	public Task getTask(UUID id) {
		return taskRepo.findById(id).orElse(new Task());
	}
	
	public Task addOrUpdateTask(Task task) {
		try {
			if(task.getPicture() != null) {				
				task.setPicture(ImageResizer.resize(task.getPicture(), 320, 240));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return taskRepo.save(task);
	}

	public Task setPicture(UUID id, byte[] picture) {
		Task task = getTask(id);
		task.setPicture(picture);
		return this.addOrUpdateTask(task);
	}
	
}
