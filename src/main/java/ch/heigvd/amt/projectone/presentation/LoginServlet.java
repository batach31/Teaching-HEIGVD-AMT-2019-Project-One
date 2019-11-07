package main.java.ch.heigvd.amt.projectone.presentation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="LoginServlet",urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet{

    //private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*String username = req.getParameter("username");
        String password = req.getParameter("password");

        String error = "";

        if (user.checkPassword(username, password)) {
            errors = "Wrong username or password";
        }

        if (errors.size() == 0) {
            Character user = characterManager.getCharacterByUsername(username);
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.setAttribute("errors", error);
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        }
    }*/
    }

}
