package com.beejee.task.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "USERS")
public class User implements Serializable {

	private static final long serialVersionUID = 613600857436392077L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String login;
	private String password;
}
