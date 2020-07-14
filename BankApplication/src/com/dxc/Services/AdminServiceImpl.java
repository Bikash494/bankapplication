package com.dxc.Services;

import java.util.List;

import com.dxc.Dao.AdminDaoImpl;
import com.dxc.Dao.IAdminDao;
import com.dxc.pojo.Bankuser;

public class AdminServiceImpl implements IAdminImpl  {

	IAdminDao dao = new AdminDaoImpl();

	public boolean authenticate(String adminname, String password)
	{
		System.out.println("adminservice - authenticate..");
		return dao.authenticate(adminname, password);
	}
	
	public void addbankuser(Bankuser s)
	{	
		System.out.println("userservice - authenticate");
		dao.addbankuser(s);
	}
	
	public float userbalance(Bankuser u)
	{	
		System.out.println("bankuser call");
		return dao.userbalance(u);
	}
	public boolean removeuser(int accno)
	{
		System.out.println("remove user call");
		return dao.removeuser(accno);
	}
	public void updateuser(Bankuser up)
	{	
		System.out.println("update user call");
		dao.updateuser(up);
	}
	
	public boolean finduser(int accno)
	{
		return dao.finduser(accno);
	}
	public List<Bankuser> getAlluser()
	{
		System.out.println("get all user");
		return dao.getAlluser();
	}
}
