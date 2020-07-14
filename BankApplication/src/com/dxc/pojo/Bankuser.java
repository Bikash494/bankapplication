package com.dxc.pojo;

public class Bankuser {
	
	private int Accno;
	private String username;
	private String password;
	private float balance;
	
	public Bankuser() {
				
	}

	public Bankuser(int accno, String username, String password, float balance) {
		super();
		Accno = accno;
		this.username = username;
		this.password = password;
		this.balance = balance;
	}

	public int getAccno() {
		return Accno;
	}

	public void setAccno(int accno) {
		Accno = accno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	
}
