package com.amsoft.controllers.order;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amsoft.dao.DaoFactory;
import com.amsoft.dao.mysql.DaoFactoryMysql;
import com.amsoft.models.Order;
import com.amsoft.models.OrderDetail;
import com.amsoft.models.Product;
import com.amsoft.services.ProductService;

@MultipartConfig
@WebServlet("/cart")
public class OrderController extends HttpServlet {
    DaoFactory daoFactory = new DaoFactoryMysql();
    ProductService productService = new ProductService(daoFactory);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (PrintWriter out = resp.getWriter()) {
            String slug = req.getParameter("slug");
            Integer quantity = Integer.parseInt(req.getParameter("quantity"));
            String size = req.getParameter("size");
            Product product = productService.findBySlug(slug);
            if (product != null) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setQuantity(quantity);
                orderDetail.setProduct(product);
                orderDetail.setPrice(product.getPrice());
                orderDetail.setTotal(product.getPrice() * quantity);
                orderDetail.setSize(size);
                HttpSession session = req.getSession();
                Order order;
                if (session.getAttribute("cart") == null) {
                    order = new Order();
                    session.setAttribute("cart", order);
                } else {
                    order = (Order) session.getAttribute("cart");
                }
                order.addOrderDetail(orderDetail);
                session.setAttribute("cartQuantity", order.getTotalQuantity());
                out.print("Producto agregado al carrito");
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }

}
