/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import java.io.IOException;
import javadbc.Context;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author V&V
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    
    private UserDaoInter userDao = Context.InstanceUserDao();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String phone = request.getParameter("password");
            String email = request.getParameter("email");
            User user = userDao.findByEmailAndPassword(email, phone);
            if (user == null) {
                throw new IllegalArgumentException("Email or password is incorrect!");
            }
            request.getSession().setAttribute("loggedInUser", user);
            response.sendRedirect("users");
            
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("error?msg=" + ex.getLocalizedMessage());
        }
    }
    
}
