package com.amsoft.controllers.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amsoft.dao.DaoFactory;
import com.amsoft.dao.mysql.DaoFactoryMysql;
import com.amsoft.models.Customer;
import com.amsoft.services.CustomerService;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    DaoFactory daoFactory = new DaoFactoryMysql();
    CustomerService customerService = new CustomerService(daoFactory);

    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String doi = req.getParameter("doi");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Customer customer = new Customer(1l,doi,firstName,lastName,address,email,password);
        customerService.save(customer);
        resp.sendRedirect("register");
    }

}
