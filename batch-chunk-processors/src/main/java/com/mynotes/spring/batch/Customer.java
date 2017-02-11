package com.mynotes.spring.batch;

import java.util.Calendar;

public class Customer {
	
	
    private String name;
	
	private int age;
	
	private Calendar creationDate;

	public Customer(String name, int age, Calendar creationDate) {
		super();
		this.name = name;
		this.age = age;
		this.creationDate = creationDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", age=" + age + ", creationDate=" + creationDate + "]";
	}
	

}
