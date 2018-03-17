package com.skip.challenge.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Customer {

	private int id;
	private String email;
	private String name;
	private String address;
	private LocalDateTime creation;
	private String password;

	public Customer() {
	}

	public Customer(int id, String name, String address, LocalDateTime creation, String password,String email) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.creation = creation;
		this.password = password;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDateTime getCreation() {
		return creation;
	}

	public void setCreation(LocalDateTime creation) {
		this.creation = creation;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String displayParsedDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm");
		return this.creation.format(formatter);
	}

}
