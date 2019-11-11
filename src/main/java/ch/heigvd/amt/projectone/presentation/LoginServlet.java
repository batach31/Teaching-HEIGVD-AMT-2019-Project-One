package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.dao.CustomerManager;
import ch.heigvd.amt.projectone.model.Customer;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @EJB
    private CustomerManager customerManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String error = "";

        try {

            if (!customerManager.verifyPassword(username, password)) {
                error = "Wrong username or password";
            }

            if (error.length() == 0) {
                Customer customer = customerManager.getCustomerByPseudo(username);
                HttpSession session = req.getSession(true);
                session.setAttribute("customer", customer);
                resp.sendRedirect(req.getContextPath() + "/test");
            } else {
                req.setAttribute("error", error);
                req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
            }
        }catch (Exception ex){

            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        }
    }
}