<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

		<h1>DISPLAY OF ALL THE USER</h1>
                  <c:forEach var="bu" items="${list}">
                  <table>
                      <tr>
                         <td>
                               <h5><c:out value="${bu.getAccno()}"></c:out></h5>
                         </td>
                         <td>
                               <h5><c:out value="${bu.getUsername()}"></c:out></h5>
                         </td>
                                                     
                           <td>
                               <h5><c:out value="${bu.getPassword()}"></c:out></h5>
                          </td> 
                          
                          <td>
                               <h5><c:out value="${bu.getBalance()}"></c:out></h5>
                          </td>     
                        </tr>              
                       
                  </table>
                  
                  </c:forEach>

</body>
</html>