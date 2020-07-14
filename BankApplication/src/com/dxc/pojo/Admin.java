package com.dxc.pojo;

public class Admin {
	
	private int Id;
	private String Name;
	private String password;
		
	public Admin() {
		
	}

	public Admin(int id, String name, String password) {
		super();
		Id = id;
		Name = name;
		this.password = password;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}


