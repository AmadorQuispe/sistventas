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

import com.amsoft.models.Order;

@MultipartConfig
@WebServlet("/cart/manage")
public class UpdateOrderController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "remove":
                removeOrderDetail(req, resp);
                break;
            case "update":
                updateQuantity(req, resp);
                break;
            default:
                break;
        }
    }

    private void updateQuantity(HttpServletRequest req, HttpServletResponse resp) {
        try (PrintWriter out = resp.getWriter()) {
            String slug = req.getParameter("slug");
            String size = req.getParameter("size");
            Integer numberIn = Integer.parseInt(req.getParameter("quantity"));
            HttpSession session = req.getSession();
            Order order = (Order) session.getAttribute("cart");
            if (order != null) {
                order.updateQuantity(slug, size, numberIn);
                session.setAttribute("cartQuantity", order.getTotalQuantity());
                out.print("Producto actualizado");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeOrderDetail(HttpServletRequest req, HttpServletResponse resp) {
        try (PrintWriter out = resp.getWriter()) {
            String slug = req.getParameter("slug");
            String size = req.getParameter("size");
            HttpSession session = req.getSession();
            Order order = (Order) session.getAttribute("cart");
            if (order != null) {
                order.removeOrderDetail(slug, size);
                session.setAttribute("cartQuantity", order.getTotalQuantity());
                out.print("Producto eliminado");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
