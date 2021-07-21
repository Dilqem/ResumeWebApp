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
import java.text.SimpleDateFormat;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javadbc.Context;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Request;

/**
 *
 * @author V&V
 */
@WebServlet(name = "UserController", urlPatterns = {"/users"})
public class UserController extends HttpServlet {
    
    private UserDaoInter userDao = Context.InstanceUserDao();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        String nationalityIdStr = request.getParameter("nid");
//        Integer nationalityId = null;
//        if (nationalityIdStr != null && nationalityIdStr.trim().isEmpty()) {
//            nationalityId = Integer.parseInt(nationalityIdStr);
//        }
//        List<User> list = userDao.getAll(name, surname, nationalityId);
//        request.setAttribute("list", list);
        request.getRequestDispatcher("users.jsp").forward(request, response);
         
    }
    
   /* @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String phone = request.getParameter("userPhone");
            String email = request.getParameter("userEmail");
            String address = request.getParameter("userAddress");
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
            
            java.util.Date birthdate = sdf.parse(request.getParameter("userBirthdate"));
            Long l = birthdate.getTime();
            java.sql.Date bd = new java.sql.Date(l);
            
            int id = Integer.valueOf(request.getParameter("id"));
            
            User u = userDao.getById(id);
            u.setAddress(address);
            u.setPhone(phone);
            u.setBirthDate(bd);
            u.setEmail(email);
            
            userDao.updateUser(u);
            response.sendRedirect("userdetail.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
}
