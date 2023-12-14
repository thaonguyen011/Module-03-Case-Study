package com.example.case_study.controller;

import com.example.case_study.model.entity.User;
import com.example.case_study.model.service.impl.UserService;
import com.example.case_study.model.utils.login.LoginManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="ForgetPassController", urlPatterns = "/forget-password")
public class ForgetPassController extends HttpServlet {
    private User user;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sendCodeEmail(req, resp);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/forget_pass.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int code = Integer.parseInt(req.getParameter("code"));
        boolean validateCode = LoginManager.getInstance().validateCode(user.getEmail(), code);
        PrintWriter writer = resp.getWriter();
        writer.write("<html>");
        if (validateCode) {
            writer.write("<p>Validate successful </p>");
            RequestDispatcher dispatcher = req.getRequestDispatcher("user/change_pass.jsp");
            HttpSession session = req.getSession();
            session.setAttribute("forgetUser", user);
            dispatcher.forward(req, resp);
        } else {
            writer.write("<p>Validate unsuccessful</p>");
        }
        writer.write("</html>");

    }

    private void sendCodeEmail(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = new UserService();
        user = userService.getUserByUsername(req.getParameter("username"));
        LoginManager.getInstance().sendCodeEmail(user.getEmail());
    }
}
