<%-- 
    Document   : User
    Created on : 04.07.2021, 12:42:17
    Author     : V&V
--%>

<%@page import="java.util.List"%>
<%@page import="com.company.entity.User"%>
<%@page import="javadbc.Context"%>
<%@page import="com.company.dao.inter.UserDaoInter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link href="WEB-INF/assets/css/users.css" rel="stylesheet" type="text/css"/>-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
              crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/494e6df4e7.js" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
        <script src="WEB-INF/assets/js/users.js"></script>
        <title>JSP Page</title>
    </head>
    <body> 
        <%
            UserDaoInter userDao = Context.InstanceUserDao();
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String nationalityIdStr = request.getParameter("nid");
            Integer nationalityId = null;
            if (nationalityIdStr != null && nationalityIdStr.trim().isEmpty()) {
                nationalityId = Integer.parseInt(nationalityIdStr);
            }
            List<User> list = userDao.getAll(name, surname, nationalityId);
            // List<User> list = (List<User>) request.getAttribute("list");
        %>
        <div class="container mycontainer">
            <div class="row">
                <div class="col-4">
                    <form action="UserController" method="GET">
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <input onkeyup="writeWhatIamTyping()" id="whatIamTyping" placeholder="Enter name" class="form-control" type="text" name="name" value=""/>
                            text here:
                            <span id="typing"></span>
                        </div>
                        <div class="form-group">
                            <label for="surname">Surname:</label>
                            <input placeholder="Enter surname" class="form-control" type="text" name="surname" value=""/>
                        </div>

                        <input class="btn btn-primary" type="submit" name="search" value = "Search"/>
                    </form>
                </div>
            </div>
            <hr/>
            <div>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Nationality</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (User u : list) {
                        %>
                        <tr>
                            <td><%=u.getName()%></td>
                            <td><%=u.getSurname()%></td>
                            <td><%=u.getNationality().getName() == null ? "N/A" : u.getNationality().getName()%></td>
                            <td> 
                                <input type="hidden" name="id" value="<%=u.getId()%>">
                                <button class="btn btn-danger" data-toggle="modal" type="submit" data-target="#exampleModal" 
                                       value="delete" name="action" onclick="setIdForDelete(<%=u.getId()%>)">
                                <i class="fas fa-trash-alt"></i>
                                </button>
                            </td>
                            <td>
                                <form action="userdetail" method="GET">
                                    <input type="hidden" name="id" value="<%=u.getId()%>"/>
                                    <input type="hidden" name="action" value="update"/>
                                    <button class="btn btn-success" type="submit" value="update" name="action" />
                                    <i class="fas fa-pen-square"></i>
                                    </button>
                                </form>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>

                    </div>
                    <div class="modal-body">
                        Are you sure?
                    </div>
                    <div class="modal-footer">
                        <form action="userdetail" method="POST">
                            <input type="hidden" id="idForDelete" name="id" value=""/>
                            <input type="hidden" name="action" value="delete"/>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <input type="submit" class="btn btn-danger" value="Delete"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
