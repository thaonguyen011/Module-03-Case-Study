package com.example.case_study.controller.signUpController;

import com.example.case_study.model.entity.Subscription;
import com.example.case_study.model.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/signup/planform", "/signup/editPlan"})
public class PlanFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("planform.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String plan = req.getParameter("plan");
        HttpSession session = req.getSession();
        session.setAttribute("plan", plan);
        User user = (User) session.getAttribute("newUser");
        Subscription subscription = new Subscription();
        subscription.setName(plan);
        subscription.setUserId(user.getId());

        resp.sendRedirect("/signup/paymentPicker");
    }
}
