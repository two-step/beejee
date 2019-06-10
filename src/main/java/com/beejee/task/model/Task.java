package com.beejee.task.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Task implements Serializable{

	private static final long serialVersionUID = 7072652101199993831L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private String email;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	private byte[] picture;
	
	private String description;
	
	private String status;
}
