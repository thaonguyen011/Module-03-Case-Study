package com.example.case_study.controller.signUpController;

import com.example.case_study.model.entity.User;
import com.example.case_study.model.service.impl.UserService;
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


@WebServlet("/signup/regform")
public class RegFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("signup_email",req.getAttribute("signup_email"));
        RequestDispatcher dispatcher = req.getRequestDispatcher("regform.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserService userService = new UserService();
        boolean existEmail = userService.isExistUser(email);
        req.setAttribute("existEmail", existEmail);
        RequestDispatcher dispatcher;
        Validator emailValidator = new EmailRegexValidator(email);
        boolean emailCheck = emailValidator.isCheck();

        if (!existEmail) {
            if (emailCheck) {
                User newUser = new User();
                newUser.setUsername(email);
                newUser.setPassword(password);
                newUser.setEmail(email);

                HttpSession session = req.getSession();
                session.setAttribute("newUser", newUser);

                userService.insert(newUser);

                dispatcher = req.getRequestDispatcher("signup.jsp");
            } else {
                req.setAttribute("emailCheck", emailCheck);
                dispatcher = req.getRequestDispatcher("regform.jsp");
            }

        } else {
            dispatcher = req.getRequestDispatcher("regform.jsp");
        }
        dispatcher.forward(req,resp);


    }

}
