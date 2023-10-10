package com.amsoft.controllers;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amsoft.dao.DaoFactory;
import com.amsoft.dao.mysql.DaoFactoryMysql;
import com.amsoft.models.Order;
import com.amsoft.models.UserDetail;
import com.amsoft.services.OrderService;

@WebServlet("/checkout")
public class CheckoutController extends HttpServlet {
    DaoFactory daoFactory = new DaoFactoryMysql();
    OrderService orderService = new OrderService(daoFactory);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null && session.getAttribute("cart") != null) {
            UserDetail user = (UserDetail) session.getAttribute("user");
            Order order = (Order) session.getAttribute("cart");
            if (user.getCustomerId() != null && order.getOrderDetails().size() > 0) {
                order.setCustomerId(user.getCustomerId());
                order.setPaymentId(Math.round(Math.random() * 100));
                order.setIsPaid(true);
                order.setRequiredDate(LocalDate.now());
                order.setStatus("Pagado");
                orderService.save(order);
                session.removeAttribute("cartQuantity");
                session.removeAttribute("cart");
                req.getRequestDispatcher("confirm.jsp").forward(req, resp);
            }

        }
        
    }

}
