package com.example.case_study.controller.signUpController;

import com.example.case_study.model.entity.Credit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signup/creditOption")
public class CreditOptionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("creditOption.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String cardNumber = req.getParameter("cardNumber");
       String expireDate  = req.getParameter("expireDate");
       String cvv = req.getParameter("cvv");
       String firstName = req.getParameter("firstName");
       String lastName = req.getParameter("lastName");

        Credit credit = new Credit(cardNumber, expireDate, cvv, firstName, lastName);

        HttpSession session = req.getSession();
        session.setAttribute("credit", credit);

        //luu vao database;
    }
}
