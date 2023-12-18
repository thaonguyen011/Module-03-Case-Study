package com.example.case_study.controller.loginController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="BlockUserController", urlPatterns = "/block-user")
public class BlockUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("<html>");
        writer.write("<p> Block user " + req.getParameter("username") + " successful</p>");
        writer.write("<a href=\"/users?action=login\">Login</a>");
        writer.write("</html>");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        HttpSession session = req.getSession();
        session.setAttribute(username, true);
    }
}
