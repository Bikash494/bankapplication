package com.dxc.Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.Services.AdminServiceImpl;
import com.dxc.pojo.Bankuser;

@WebServlet("/Admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AdminServiceImpl adminService=new AdminServiceImpl();
	
	String errorMessage="";
	String message="";
	String action="";
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	System.out.println("servlet");
    	String temp=request.getParameter("btn");
    	if(temp!=null)
    		action=temp;
    	if(action.equals("Adminlogin"))
		{
    		System.out.println("adminlogin servlet");
			boolean authenticated=false;
			HttpSession session=request.getSession();
			String adminname=request.getParameter("admin");
			String password=request.getParameter("password");
			authenticated=adminService.authenticate(adminname,password);
			System.out.println("inside authenticated");
			if(authenticated==true) 
			{
				message="login succesfully";
				System.out.println("login success");
				session.setAttribute("message", message);
				response.sendRedirect("adminmenu.jsp");
			}
			else
			{
				errorMessage="Invalid login!";
				System.out.println("login failed");
				session.setAttribute("errorMessage", errorMessage);
				response.sendRedirect("error.jsp");
			}
	}
	}
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {	
    	String viewmessage="";   
    	String action="";
    	String temp=request.getParameter("btn");
    	   if(temp!=null)
    		   action=temp;
     HttpSession session=request.getSession();	
    	   if(action.equals("adduser"))
    	   {
    		 //response.getWriter().print("call add_customer method from admin service here...");
    		System.out.println("servlet admin add user");
    		int Acc_no=Integer.parseInt(request.getParameter("accno"));
   			String username=request.getParameter("username");
   			String password=request.getParameter("password");
   			float balance=Float.parseFloat(request.getParameter("balance"));
   			
   			Bankuser s1=new Bankuser(Acc_no, username, password, balance);
   			adminService.addbankuser(s1);
   			viewmessage="user added successfully!";
   			session.setAttribute("viewmessage",viewmessage);
   			response.sendRedirect("view.jsp");
    		      		 
    	   }	
    	   
    		   else if(action.equals("getuserbal")) {
    			   
    			System.out.println("call user balance");
    		   //response.getWriter().print("call get_cust_bal method from admin service here...");
    			int Acc_no=Integer.parseInt(request.getParameter("accno")); 
    			Bankuser u1=new Bankuser();
    			u1.setAccno(Acc_no);
    			System.out.println("get user balance");
    			float balance=adminService.userbalance(u1);
    			viewmessage="avail balance is"+balance;
    			u1.setBalance(balance);
    			System.out.println(u1.getBalance());
    			session.setAttribute("viewmessage", viewmessage);
    			response.sendRedirect("view.jsp");
    			 			   
    	   }
    		   else if(action.equals("removeuser")) {
    		  //response.getWriter().println("call remove_customer method from admin service here...");
    		  int accno=Integer.parseInt(request.getParameter("accno"));
    		  boolean removeStatus=adminService.removeuser(accno);
    		  if (removeStatus==true){
    			   viewmessage="user removed";	
    		   }
    		  else {
    			   viewmessage="user not found";
        		   } 
    		  session.setAttribute("viewmessage",viewmessage);
  			response.sendRedirect("view.jsp");
    		   }
    	else if(action.equals("find_user")){
    		int Accno=Integer.parseInt(request.getParameter("accno"));
			session.setAttribute("Acc_number", Accno);
			boolean findStatus=adminService.finduser(Accno);
			if(findStatus==true)
			{
				response.sendRedirect("updateuser1.jsp");
			}
			else
			{
				viewmessage="user not found!";
				session.setAttribute("viewmessage", viewmessage);
				response.sendRedirect("view.jsp");
    		 }
    	}
    	else if(action.equals("updateuser")) {
    		System.out.println("update user enter");
    		   //response.getWriter().println("call update_customer method from admin service here...");
    		int Accno=(int)session.getAttribute("Acc_number");
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			Float balance=Float.parseFloat(request.getParameter("balance"));
			
			Bankuser s=new Bankuser(Accno, username,password,balance);
			
			adminService.updateuser(s);
			viewmessage="user updated!";
			System.out.println("user update succefully");
			session.setAttribute("viewmessage", viewmessage);
			response.sendRedirect("view.jsp");
    	}
    
    	else {
    		   //response.getWriter().println("call show_all_customers method from admin service here...");
    			List<Bankuser> list=adminService.getAlluser();
    			session.setAttribute("list", list);
    			response.sendRedirect("showallcust.jsp");
    		}
}

}