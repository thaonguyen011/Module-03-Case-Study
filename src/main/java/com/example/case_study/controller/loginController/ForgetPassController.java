package com.example.case_study.controller.loginController;

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

@WebServlet("/login/forgetPassword")
public class ForgetPassController extends HttpServlet {
    private User user;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        String username = req.getParameter("username");
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();
        if (userService.isExistUser(username)) {
            if (LoginManager.getInstance().isBlockedUser(username)) {
                resp.sendRedirect("/login/block");
            } else {
                LoginManager.getInstance().sendCodeEmail(username);

                user = userService.getUserByUsername(username);
                dispatcher =  req.getRequestDispatcher("formValidate.jsp");
                dispatcher.forward(req, resp);
            }
        } else {
            session.setAttribute("auth", 0);
            resp.sendRedirect("/login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) action = "validate";

        switch (action) {
            case "form":
                form(req, resp);
                break;
            case "validate":
                validate(req, resp);
                break;
            default:
                break;
        }

    }

    private void form(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        String form = req.getParameter("form");
        String email = req.getParameter("emailAddress");
        LoginManager.getInstance().sendCodeEmail(email);
        RequestDispatcher dispatcher;
        if (form.equals("email")) {
            dispatcher = req.getRequestDispatcher("forgetPass.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void validate(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        String code = req.getParameter("code").trim();
        boolean validateCode = LoginManager.getInstance().validateCode(user.getId(), code);
        if (validateCode) {
            HttpSession session = req.getSession();
            session.setAttribute("forgetUser", user);
            req.setAttribute("codeValidate", true);
            resp.sendRedirect("/login/changePass");
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("forgetPass.jsp");
            req.setAttribute("codeValidate", false);
            dispatcher.forward(req, resp);
        }
    }

}
