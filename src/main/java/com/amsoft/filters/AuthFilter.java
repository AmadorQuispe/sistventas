package com.amsoft.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebFilter(filterName = "AuthFilter", urlPatterns = "/checkout")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if(session.getAttribute("user")==null){
            String urlRequest = req.getRequestURL().toString();
            req.setAttribute("destination", urlRequest);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }
        
        chain.doFilter(request, response);
    }
}
