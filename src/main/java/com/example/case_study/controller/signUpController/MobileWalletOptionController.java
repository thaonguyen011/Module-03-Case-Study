package com.example.case_study.controller.signUpController;

import com.example.case_study.model.entity.User;
import com.example.case_study.model.service.impl.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signup/mobileWalletOption")
public class MobileWalletOptionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("mobileWalletOption.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        HttpSession session = req.getSession();
        User newUser = (User) session.getAttribute("newUser");
        newUser.setPhone(phone);

       UserService userService = new UserService();
       userService.update(newUser);

        // chuyen trang thanh toan bang qr cua momo
        resp.sendRedirect("/momo");
    }
}
