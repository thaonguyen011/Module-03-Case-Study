package com.example.case_study.controller.loginController;

import com.example.case_study.model.entity.User;
import com.example.case_study.model.service.impl.UserService;
import com.example.case_study.model.utils.login.Validator;
import com.example.case_study.model.utils.regexValidator.PassRegexValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login/changePass")
public class ChangePassController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       RequestDispatcher dispatcher = req.getRequestDispatcher("changePassword.jsp");
       dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newPass = req.getParameter("newPass");
        String newPassAgain = req.getParameter("newPassAgain");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("forgetUser");
        if (newPassAgain.equals(newPass)) {
            UserService userService = new UserService();
            user.setPassword(newPass);
            userService.update(user);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("changePassword.jsp");
            req.setAttribute("isNotCatch", true);
            dispatcher.forward(req, resp);
        }
    }
}
