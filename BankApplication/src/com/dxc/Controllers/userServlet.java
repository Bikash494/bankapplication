package com.dxc.Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.Services.UserServiceImpl;
import com.dxc.pojo.Bankuser;
import com.dxc.pojo.Transaction;


@WebServlet("/user")
public class userServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UserServiceImpl userService=new UserServiceImpl();
  	String action="";
  	String errorMessage="";
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
    	System.out.println("servlet");
    	String temp=request.getParameter("btn");
    	if(temp!=null)
		action=temp; 		
    		if(action.equals("Userlogin"))
    		{
    			System.out.println("userlogin servlet");
    			boolean authenticated=false;
    			HttpSession session=request.getSession();
    			String username=request.getParameter("user");
    			String password=request.getParameter("password");
    			authenticated=userService.authenticateuser(username, password);
    			System.out.println("inside authenticated");
			if(authenticated==true) {
				System.out.println("login successfully");
				response.sendRedirect("usermenu.jsp");
			}
			else
			{
				errorMessage="Invalid login!";
				session.setAttribute("errorMessage", errorMessage);
				response.sendRedirect("error.jsp");
			}
	}
	}
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {	
    	   String action="";
    	   String viewmessage="";
    	   String temp=request.getParameter("userbtn");
    	   if(temp!=null)
    		   action=temp;   
    	   HttpSession session=request.getSession();	
    	   if(action.equals("deposite")) {
    		  // response.getWriter().print("call deposite method from user service here...");
    		   int accno=Integer.parseInt(request.getParameter("accno"));
    		   double amount=Double.parseDouble(request.getParameter("amount"));
    		   Bankuser u1=new Bankuser();
    		   boolean bal = userService.depositeAmount(accno, amount);
    		   if(bal==true) {
    			   viewmessage="Amount added successfully";
    			   session.setAttribute("viewmessage", viewmessage);
    			   response.sendRedirect("view.jsp");
    		} else {
    			viewmessage="Invalid Account number!";
  			   session.setAttribute("viewmessage", viewmessage);
  			   response.sendRedirect("view.jsp");
    		}
    	}
    			
    		else if(action.equals("withdraw")) {
    		  // response.getWriter().print("call deposite method from user service here...");
    		   int accno=Integer.parseInt(request.getParameter("accno"));
    		   double amount=Double.parseDouble(request.getParameter("amount"));
    		   Bankuser u1=new Bankuser();
    		   boolean bal = userService.WithdrawAmount(accno, amount);
    		   if(bal==true) {
    			   viewmessage="money withdraw successfully";
    			   session.setAttribute("viewmessage", viewmessage);
    			   response.sendRedirect("view.jsp");
    		} 
    		   else {
    			viewmessage="Invalid Account number!";
  			   session.setAttribute("viewmessage", viewmessage);
  			   response.sendRedirect("view.jsp");
    		}
    	}		       	   
    	else if(action.equals("transfer"))
    		{		
    			int accno=Integer.parseInt(request.getParameter("accno"));
    			String password=request.getParameter("password");
    			
    			int taccno=Integer.parseInt(request.getParameter("taccno"));
    			double tamount=Double.parseDouble(request.getParameter("tamount"));    				
    			boolean c1=userService.transfer(accno, taccno, tamount);
    				if(c1==true)
    				{
    					try {                            	
    						viewmessage="Amount Transfered Sucessfully.....!!!";
    						session.setAttribute("viewmessage",viewmessage);
    						response.sendRedirect("view.jsp");
    						
    			         	}catch (IOException e) {
    			         		e.printStackTrace();
    			         	}
    				}
    		 }
    		   
    	   else if(action.equals("checkbalance")) {
    		   //response.getWriter().print("call check_balance method from user service here...");
    		   int accno=Integer.parseInt(request.getParameter("accno")); 
    		   Bankuser u1=new Bankuser();
    		   u1.setAccno(accno);
    		  
    		   double balance=userService.Checkbalance(u1);
    		   viewmessage="avail balance is"+balance;
    		   System.out.println(balance);
    		   if(balance==-1)
    		   {
    			   session.setAttribute("viewmessage", "user not found");   			
    		   }
    		   else
    		   {
    		   session.setAttribute("viewmessage", balance);
    		   }
    		   response.sendRedirect("view.jsp");    	   
    	   }
    	else if(action.equals("printstatement")) { 
    		
    		//response.getWriter().print("call print_mini_statement method from user service here...");    		
			int accno=Integer.parseInt(request.getParameter("accno"));
			List<Transaction> list=userService.TransactionDetails(accno);
			session.setAttribute("list", list);
			response.sendRedirect("transactiondetails.jsp");
				
	    	}
    	else if(action.equals("changepassword")) {
    		//response.getWriter().print("call change_password method from user service here...");
    		int accno=Integer.parseInt(request.getParameter("accno"));
    		String password=request.getParameter("password");
    		  boolean changeStatus=userService.changePassword(password, accno);
     		 if (changeStatus==true){
     			   viewmessage="password change";	
     		   }
     		  else {
     			   viewmessage="password not change";
         		   } 
     		  	session.setAttribute("viewmessage",viewmessage);
   				response.sendRedirect("view.jsp");
     		   }     	   
    	   }
    }


