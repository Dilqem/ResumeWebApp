/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "UserDetailController", urlPatterns = {"/userdetail"})
public class UserDetailController extends HttpServlet {

    private UserDaoInter userDao = Context.InstanceUserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userIdStr = request.getParameter("id");
            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Id is not specified");
            }

            int userId = Integer.parseInt(userIdStr);
            User u = userDao.getById(userId);
            if (u == null) {
                throw new IllegalArgumentException("There is no user with this id.");
            }
            // request.setAttribute("owner", true);
            request.setAttribute("user", u);
            request.getRequestDispatcher("userdetail.jsp").forward(request, response);
        } catch (Exception ex) {
            response.sendRedirect("error?msg=" + ex.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String action = request.getParameter("action");
        if (action.equals("update")) {
            try {
                String phone = request.getParameter("userPhone");
                String email = request.getParameter("userEmail");
                String address = request.getParameter("userAddress");
                SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");

                java.util.Date birthdate = sdf.parse(request.getParameter("userBirthdate"));
                Long l = birthdate.getTime();
                java.sql.Date bd = new java.sql.Date(l);

                User u = userDao.getById(id);
                u.setAddress(address);
                u.setPhone(phone);
                u.setBirthDate(bd);
                u.setEmail(email);

                userDao.updateUser(u);
            } catch (ParseException ex) {
                Logger.getLogger(UserDetailController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("delete")) {
            userDao.removeUser(id);
        }
        response.sendRedirect("users.jsp");
    }

}
