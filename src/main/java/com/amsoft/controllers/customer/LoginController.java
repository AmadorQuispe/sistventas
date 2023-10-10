package com.amsoft.controllers.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amsoft.dao.DaoFactory;
import com.amsoft.dao.mysql.DaoFactoryMysql;
import com.amsoft.models.UserDetail;
import com.amsoft.services.CustomerService;

@MultipartConfig
@WebServlet("/login")
public class LoginController extends HttpServlet {
    DaoFactory daoFactory = new DaoFactoryMysql();
    CustomerService customerService = new CustomerService(daoFactory);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String destination = req.getParameter("destination");
        UserDetail userDetail = customerService.login(email, password);
        HttpSession session = req.getSession();
        if (userDetail != null) {
            session.setAttribute("user", userDetail);
            String pagDestination = destination == null ? req.getContextPath() : destination;
            System.out.println(pagDestination);
            resp.sendRedirect(pagDestination);
        } else {
            req.setAttribute("message", "Usuario y/o contraseña inccorrecta");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

}
