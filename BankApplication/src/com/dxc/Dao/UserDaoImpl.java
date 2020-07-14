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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.dxc.pojo.Bankuser;
import com.dxc.pojo.Transaction;

public class UserDaoImpl implements IUserDao {
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
			public boolean authenticateuser(String username, String password)
				{
					System.out.println("inside authenticate");
					try
					{

						 Statement stmt = conn.createStatement();
						 ResultSet rset = stmt.executeQuery("select username,password from bankuser");
						
						 while(rset.next())
						 {
							 System.out.println(rset.getString(1)+" "+rset.getString(2));
							 System.out.println(username+" "+password);
							 if(rset.getString(1).equals(username) && rset.getString(2).equals(password))
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
			
			public boolean depositeAmount(int accno,double amount) {
				double balance=0;
				String Type="deposite";
				try {
					System.out.println("accno=");
					PreparedStatement pstmt=conn.prepareStatement("select * from bankuser where accno=?");
					pstmt.setInt(1, accno);
					ResultSet rs = pstmt.executeQuery();
					System.out.println("entering to  accno");
					while(rs.next())
					{
						balance=rs.getDouble(4);
						balance=balance+amount;
						System.out.println(balance);
					}
					
					PreparedStatement pstmt1= conn.prepareStatement("update bankuser set balance=? where accno=?");
					pstmt1.setDouble(1, balance);
					pstmt1.setInt(2,accno);
					pstmt1.executeUpdate();	
				
				
				return true;
				}
				
				catch(SQLException e) {
					e.printStackTrace();
				}	return false;
				
			}
			public double Checkbalance(Bankuser u1)
			{	
				int accno=u1.getAccno();
				double balance=0;
				try {
				PreparedStatement pstmt=conn.prepareStatement("select * from bankuser");				
				ResultSet rset=pstmt.executeQuery();
				while(rset.next())
				{
					if(accno==rset.getInt(1))
		  			{
		  				 balance=rset.getDouble(4);
		  			}
				}				
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
				System.out.println(balance);
				
				return balance;
		}
			
			public boolean 	WithdrawAmount(int accno,double amount) {
				double balance=0;
				String type="withdraw";
				try {
					System.out.println("accno=");
					PreparedStatement pstmt=conn.prepareStatement("select * from bankuser where accno=?");
					pstmt.setInt(1, accno);
					ResultSet rs = pstmt.executeQuery();
					System.out.println("entering to  accno");
					while(rs.next())
					{
						balance=rs.getDouble(4);
						balance=balance-amount;
						System.out.println(balance);
					}
					
					PreparedStatement pstmt1= conn.prepareStatement("update bankuser set balance=? where accno=?");
					pstmt1.setDouble(1, balance);
					pstmt1.setInt(2,accno);
					pstmt1.executeUpdate();	
					
					
										
				return true;
				} 
				
				catch(SQLException e) {
					e.printStackTrace();
				}	return false;				
			}
			
			
			
			public boolean transfer(int accno,int taccno,double tamount)
			   {	
					Bankuser u1=new Bankuser();
				   System.out.println(tamount);
				   
				   this.WithdrawAmount(accno, tamount);
				   this.depositeAmount(taccno, tamount);
				   try {
					   PreparedStatement pstmt=conn.prepareStatement("Insert into transactiondata values(?,?,?)");
					   pstmt.setInt(1,accno);
					   pstmt.setString(2, "transfered");
					   pstmt.setDouble(3, tamount);
					   pstmt.execute();
					   PreparedStatement pstmt2=conn.prepareStatement("Insert into transactiondata values(?,?,?)");
					   pstmt2.setInt(1,taccno);
					   pstmt2.setString(2, "credit");
					   pstmt2.setDouble(3, tamount);
					   pstmt2.execute();
					   return true;
				 
				   } catch(SQLException e) {
					  
					   e.printStackTrace();
				   }				   				   
				   return false;
			   }
			public List<Transaction>TransactionDetails(int accno) {
				   List<Transaction> list=new ArrayList<>();
				   try {
					   Statement stmt=conn.createStatement();
					   ResultSet rs=stmt.executeQuery("select * from transactiondata");
					   while(rs.next())
					   {
						   if(accno==rs.getInt(1))
						   {
							   list.add(new Transaction(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
							}
						}
					  }catch(Exception e) {
						  e.printStackTrace();
					  }
				   return list;
			  }
			
			public boolean changePassword(String password, int accno) {

				try {
					Statement stmt = conn.createStatement();
					PreparedStatement pstmt=conn.prepareStatement("UPDATE bankuser SET Password=? WHERE accno=?");
					pstmt.setString(1,password);
					pstmt.setInt(2, accno);
					int value=pstmt.executeUpdate();
					System.out.println(value);

					if (value == 1) {
						return true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return false;				
}
}
