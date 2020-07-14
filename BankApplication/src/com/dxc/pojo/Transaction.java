package com.dxc.pojo;

public class Transaction {
	
	private int accno;
	private String transaction;
	private double transferable;
	
	public Transaction()
	{
		
	}
		
	public Transaction(int accno, String transaction, double transferable) {
		super();
		this.accno = accno;
		this.transaction = transaction;
		this.transferable = transferable;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	public double getTransferable() {
		return transferable;
	}
	public void setTransferable(double transferable) {
		this.transferable = transferable;
	}
}
