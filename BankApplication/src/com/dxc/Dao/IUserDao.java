package com.dxc.Dao;

import java.util.List;

import com.dxc.pojo.Bankuser;
import com.dxc.pojo.Transaction;

public interface IUserDao {
	
	public boolean authenticateuser(String username,String Password);
	
	public boolean changePassword(String password,int accno);
	
	public boolean depositeAmount(int accno,double amount);
	
	public boolean 	WithdrawAmount(int accno,double amount);
	
	public double Checkbalance(Bankuser u1);
	
	public boolean transfer(int accno, int taccno, double amount);	
	
	public List<Transaction> TransactionDetails(int accno);

}
