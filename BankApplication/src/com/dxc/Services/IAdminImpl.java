package com.dxc.Services;

import java.util.List;

import com.dxc.pojo.Bankuser;

public interface IAdminImpl {
	
	public  boolean authenticate(String adminname, String password);
	public void addbankuser(Bankuser s);
	public float userbalance(Bankuser u);
	public boolean removeuser(int accno);
	public void updateuser(Bankuser up);
	public boolean finduser(int accno);
	public List<Bankuser> getAlluser();
}
