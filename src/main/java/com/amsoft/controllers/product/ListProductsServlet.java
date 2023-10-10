package com.amsoft.controllers.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amsoft.dao.DaoFactory;
import com.amsoft.dao.mysql.DaoFactoryMysql;
import com.amsoft.models.Product;
import com.amsoft.services.ProductService;

@WebServlet("/product")
public class ListProductsServlet extends HttpServlet {
    DaoFactory daoFactory = new DaoFactoryMysql();
    ProductService productService = new ProductService(daoFactory);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String slug = req.getParameter("slug");
        if (slug == null) {
            List<Product> products = productService.listAll();
            req.setAttribute("products", products);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            Product product = productService.findBySlug(slug);
            if (product != null) {
                req.setAttribute("product", product);
                req.getRequestDispatcher("product.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/product");
            }

        }

    }

}
