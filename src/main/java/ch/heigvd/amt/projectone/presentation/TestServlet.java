package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="TestServlet",urlPatterns = {"/test"})
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = (Customer) req.getSession().getAttribute("customer");
        req.getSession().setAttribute("username", customer.getCustomer_pseudo());
        req.getRequestDispatcher("/WEB-INF/pages/test.jsp").forward(req, resp);
    }
}
