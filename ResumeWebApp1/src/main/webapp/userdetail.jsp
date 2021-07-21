<%-- 
    Document   : User
    Created on : 04.07.2021, 12:42:17
    Author     : V&V
--%>

<%@page import="com.company.entity.User"%>
<%@page import="javadbc.Context"%>
<%@page import="com.company.dao.inter.UserDaoInter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body> 
        <%
            UserDaoInter userDao = Context.InstanceUserDao();
            User u1 = userDao.getById(1);
//            if (request.getAttribute("owner") == null) {
//                response.sendRedirect("error.jsp?msg = page not found!");
//                return;
//            }
            User u = (User) request.getAttribute("user");
        %>
        <div>
            <form action="userdetail" method="POST">

                <input type="hidden" id="id" name="action" value="update"/>
                <label for="userEmail">Email:</label>
                <input type="text" name="userEmail" value="<%=u.getEmail()%>"/>
                <br/><br/>
                <label for="userPhone">Phone:</label>
                <input type="text" name="userPhone" value="<%=u.getPhone()%>"/>
                <br/><br/>
                <label for="userAddress">Address:</label>
                <input type="text" name="userAddress" value="<%=u.getAddress()%>"/>
                <br/><br/>
                <label for="userBirthdate">Birthdate:</label>
                <input type="text" name="userBirthdate" value="<%=u.getBirthDate()%>"/>

                <input type="submit" name="saveButton" value = "Save"/>
            </form>
        </div>
    </body>
</html>
