<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
			<form action="user" method="get">			    
				
				Enter account number  - <input type="text" name="accno"><br>
				
				Enter Password                   - <input type="text" name="password"><br> 
				
				Enter account number to transfer - <input type="text" name="taccno"><br>
				
				Enter amount to transfer         - <input type="text" name="tamount"><br>
				
				<input type="submit" name="userbtn" value="transfer">
			
			</form>

</body>
</html>