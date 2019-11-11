package ch.heigvd.amt.projectone.presentation;


import ch.heigvd.amt.projectone.dao.CustomerManager;
import ch.heigvd.amt.projectone.model.Customer;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "Test", urlPatterns = {"/test"})
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/test.jsp").forward(req, resp);
    }
}
