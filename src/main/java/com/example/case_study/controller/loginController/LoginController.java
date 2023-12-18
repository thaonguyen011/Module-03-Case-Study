package com.example.case_study.controller.loginController;

import com.example.case_study.model.entity.User;
import com.example.case_study.model.service.impl.UserService;
import com.example.case_study.model.utils.login.ILoginRequest;
import com.example.case_study.model.utils.login.LoginManager;
import com.example.case_study.model.utils.login.LoginRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getAttribute("auth") == null) {
            req.setAttribute("auth", -1);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("login/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        ILoginRequest loginRequest = new LoginRequest(username, password);
        LoginManager loginManager = LoginManager.getInstance();
        HttpSession session = req.getSession();

        int auth = loginManager.authentic(loginRequest);
        RequestDispatcher dispatcher;

        if (auth == -1) {
            UserService userService = new UserService();
            User loginUser = userService.getUserByUsername(username);
            LoginManager manager = LoginManager.getInstance();
            if (!manager.isOnlineUser(loginUser.getId())) {
                LoginManager.getInstance().addOnlineUser(loginUser.getId());
                resp.sendRedirect("/browse");
            } else {
                dispatcher = req.getRequestDispatcher("login/login.jsp");
                req.setAttribute("isOnlineUser", true);
                dispatcher.forward(req, resp);
            }
        } else {
            req.setAttribute("auth", auth);
            dispatcher = req.getRequestDispatcher("login/login.jsp");
            dispatcher.forward(req, resp);
            session.setAttribute("emailSignIn", username);
        }
    }
}
