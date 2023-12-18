package com.example.case_study.controller;

import com.example.case_study.model.entity.User;
import com.example.case_study.model.service.impl.UserService;
import com.example.case_study.model.utils.login.LoginManager;
import com.example.case_study.model.utils.login.Validator;
import com.example.case_study.model.utils.regexValidator.EmailRegexValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/main")
public class MainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        Validator emailCheck = new EmailRegexValidator(email);
        RequestDispatcher dispatcher;
        LoginManager loginManager = LoginManager.getInstance();
        if (emailCheck.isCheck()) {
            UserService userService = new UserService();

            User user = userService.getUserByUsername(email);
            boolean isExistedEmail = user != null;

            if (isExistedEmail) {
                try {
                    if (loginManager.isOnlineUser(user.getId())) {
                        req.setAttribute("isOnlineUser", true);
                        dispatcher = req.getRequestDispatcher("index.jsp");
                        dispatcher.forward(req, resp);
                    } else {
                        HttpSession session = req.getSession();
                        session.setAttribute("emailSignIn", email);
                        resp.sendRedirect("/login");
                    }
                } catch (NullPointerException ignored) {

                }

            } else {
                HttpSession session = req.getSession();
                session.setAttribute("emailSignUp", email);
                resp.sendRedirect("/signup/registration");
            }

        } else {
            req.setAttribute("emailRegex", false);
            dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
//

    }
}
