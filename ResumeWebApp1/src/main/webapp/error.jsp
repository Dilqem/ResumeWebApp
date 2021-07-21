<%-- 
    Document   : error
    Created on : 14.07.2021, 4:59:48
    Author     : V&V
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Why are you here?</title>
    </head>
    <body>
        <%=request.getParameter("msg")%>
    </body>
</html>
