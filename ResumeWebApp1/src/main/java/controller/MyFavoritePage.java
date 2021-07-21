/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.company.dao.inter.SkillDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.Skill;
import com.company.entity.User;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "MyFavoritePage", urlPatterns = {"/MyFavoritePage"})
public class MyFavoritePage extends HttpServlet {

    UserDaoInter userDao = Context.InstanceUserDao();
    SkillDaoInter skillDao = Context.InstanceSkillDao();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String name = String.valueOf(request.getAttribute("name"));
        Skill skill = new Skill(0, name);
        skillDao.insertSkill(skill);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyFavoritePage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println(skillDao.getAllSkill());
            out.println("<h1> Hello World!  </h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        User u = userDao.getById(1);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyFavoritePage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println(u);
            out.println("<h1> Hello World!  </h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
