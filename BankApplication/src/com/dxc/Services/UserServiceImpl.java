package com.dxc.Services;

import java.util.List;

import com.dxc.Dao.IUserDao;
import com.dxc.Dao.UserDaoImpl;
import com.dxc.pojo.Bankuser;
import com.dxc.pojo.Transaction;

public class UserServiceImpl implements IUserService
{
	IUserDao udao = new UserDaoImpl();
	
	public boolean authenticateuser(String username,String Password)
	{
		return udao.authenticateuser(username, Password);
	}
	
	public boolean changePassword(String password,int accno)
	{
		return udao.changePassword(password,accno);
	}
	
	public boolean depositeAmount(int accno,double amount)
	{
		return udao.depositeAmount(accno, amount);
	}
	
	public boolean 	WithdrawAmount(int accno,double amount)
	{
		return udao.WithdrawAmount(accno, amount);
	}
	
	public double Checkbalance(Bankuser u1)
	{
		return udao.Checkbalance(u1);
	}
	
	public boolean transfer(int accno, int taccno, double amount)
	{
		return udao.transfer(accno, taccno, amount);
	}
	
	public List<Transaction> TransactionDetails(int accno) {
		 
		return udao.TransactionDetails(accno);
		
	}
}
