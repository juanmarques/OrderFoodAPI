package com.skip.challenge.model;

public class Product {

	private int id;
	private int storeid;
	private String name;
	private String description;
	private double price;

	public Product() {}
	
	public Product(int id, int storeid, String name, String description, double price) {
		super();
		this.id = id;
		this.storeid = storeid;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStoreid() {
		return storeid;
	}

	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
