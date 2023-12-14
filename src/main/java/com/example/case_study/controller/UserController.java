package com.example.case_study.controller;

import com.example.case_study.model.service.IEntityService;
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
import java.io.PrintWriter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebServlet(name="UserController", urlPatterns = "/users")
public class UserController extends HttpServlet {
    private final IEntityService userService;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public UserController() {

        userService = new UserService();
        scheduler.scheduleAtFixedRate(this::clearBlock, 0, 1, TimeUnit.MINUTES);
    }

    private void clearBlock() {
        LoginManager.getInstance().clearBlock();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action ="";
        }

        switch (action) {
            case "login":
                showLoginForm(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action ="";
        }

        switch (action) {
            case "login":
                clearBlock();
                login(req, resp);
                break;
            default:
                break;
        }
    }

    private void showLoginForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/login.jsp");
        dispatcher.forward(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean isBlocked;
        try{
            HttpSession httpSession = req.getSession();
            isBlocked= (boolean) httpSession.getAttribute(username);
        } catch (NullPointerException e) {
            isBlocked = false;
        }

        ILoginRequest loginRequest = new LoginRequest(username, password);
        LoginManager loginManager = LoginManager.getInstance();
        if (isBlocked) {
            loginManager.blockUser(username);
        }
        int auth = loginManager.authentic(loginRequest);
        RequestDispatcher dispatcher;
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("<html>");
        if (auth == 0) {
            printWriter.write("<p>null</p>");
        } else if (auth == -1) {
            printWriter.write("<p>success</p>");
        } else if (auth == 6) {
            printWriter.write("<p>blocked</p>");
        }else {
           dispatcher = req.getRequestDispatcher("user/login.jsp");
           req.setAttribute("loginAttempts", auth);
           dispatcher.forward(req,resp);
        }
        printWriter.write("</html>");

    }



}
