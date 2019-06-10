package com.beejee.task.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beejee.task.model.Task;
import com.beejee.task.service.TaskService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/taskapi/tasks")
public class TaskController {
	@Autowired
	private TaskService taskService;
	
	@GetMapping
	public ResponseEntity<Collection<Task>> getTasks(){
		Collection<Task> tasks =  taskService.getAllTasks();
		return new ResponseEntity<Collection<Task>>(tasks, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Task> addTask(@RequestBody Task task){
		Task newTask = taskService.addOrUpdateTask(task);
		return new ResponseEntity<Task>(newTask, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTask(@PathVariable UUID id) {
		Task task = taskService.getTask(id);
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@RequestBody Task task, @PathVariable UUID id) {
		Task updatedTask = taskService.addOrUpdateTask(task);
		return new ResponseEntity<Task>(updatedTask, HttpStatus.OK);
	}
	
	@PostMapping("/{id}/uploadPhoto")
	public ResponseEntity<Task> uploadPersonPhoto(@PathVariable UUID id, @RequestBody byte[] picture) throws SQLException {
		Task task = taskService.setPicture(id, picture);
		return new ResponseEntity<>(task, HttpStatus.OK);
	}
}
