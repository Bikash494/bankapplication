package com.dxc.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dxc.pojo.Bankuser;


public class AdminDaoImpl implements IAdminDao {
	
private static Connection conn;
	
	Scanner sc = new Scanner(System.in);

	static {
		try                 
		  {
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  System.out.println("Driver loaded...");
		  
		  conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","qwerty");
		  System.out.println("Connected to database...");
		  }
		  catch(Exception e)
			{
			  e.printStackTrace();
			}		
	}
			public boolean authenticate(String adminname, String password)
				{
					System.out.println("inside authenticate");
					try
					{

						 Statement stmt = conn.createStatement();
						 ResultSet rset = stmt.executeQuery("select name,password from admin");
						
						 while(rset.next())
						 {
							 System.out.println(rset.getString(1)+" "+rset.getString(2));
							 System.out.println(adminname+" "+password);
							 if(rset.getString(1).equals(adminname) && rset.getString(2).equals(password))
							 {
								 return true;
							 }
							 break;
						 }
					}
					catch (Exception e) 
					{
						System.out.println("Authentication Error Occured!!!");
						e.printStackTrace();
					}
					return false;
				}
			public void addbankuser(Bankuser s){
				try {
					System.out.println("insert to database");
					PreparedStatement pstmt=conn.prepareStatement("insert into bankuser values(?,?,?,?)");
					System.out.println("inserting....");
					pstmt.setInt(1, s.getAccno());
					pstmt.setString(2, s.getUsername());
					pstmt.setString(3, s.getPassword());
					pstmt.setDouble(4, s.getBalance());
					pstmt.executeUpdate();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		public float userbalance(Bankuser u)
		{
			int accno=u.getAccno();
			float balance=0;
			Statement stmt;
			try {
				stmt=conn.createStatement();
				ResultSet rset=stmt.executeQuery("select * from bankuser");
				while(rset.next())
				{
					if(accno==rset.getInt(1));
					{
						balance=rset.getFloat(4);
					}
				}
			}
			catch(SQLException e) {
				//to do auto generation catch block
				e.printStackTrace();
			}
			System.out.println(balance);
			 return balance;			
		}
	public boolean removeuser(int accno)
	{
		boolean Status=false;
		try {
			PreparedStatement pstmt=conn.prepareStatement("delete from bankuser where accno=?");
			pstmt.setInt(1, accno);
			Status=pstmt.execute();
		}catch(SQLException e){
			e.printStackTrace();		
		}
		return !Status;
	}
	public boolean finduser(int accno) {
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from bankuser");
			while(rs.next())
			{
				if(accno==rs.getInt(1))
				{
					return true;
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public void updateuser(Bankuser up)	{
		
		try {
			
			PreparedStatement pstmt=conn.prepareStatement("update bankuser set username=?, password=?,balance=? where Accno=?");
			System.out.println("entering to upadte database");
			pstmt.setString(1, up.getUsername());
			pstmt.setString(2, up.getPassword());
			pstmt.setDouble(3, up.getBalance());
			pstmt.setInt(4, up.getAccno());
						
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
 
	}
	public List<Bankuser> getAlluser() {
		List<Bankuser> list=new ArrayList<>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from bankuser");
			while(rs.next())
			{
				list.add(new Bankuser(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getFloat(4)));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
}


