package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.dao.CustomerManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="RegisterServlet",urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @EJB
    CustomerManager customerManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String error = "";

        String pseudo = req.getParameter("username");
        String password = req.getParameter("password");
        String passwordVerify = req.getParameter("passwordVerify");
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        int age = 0;
        try {
            age = Integer.parseInt(req.getParameter("age"));
        }catch (Exception e){
            error = " il faut entrer un nombre pour l'age";
        }


        if(!password.equals(passwordVerify)){
            error += error.length() == 0 ? "Vous avez entré deux mot de passe différents" : " et vous avez entré deux mot de passe différents";
        }

        if(customerManager.getCustomerByPseudo(pseudo) != null){
            error += error.length() == 0 ? "Ce pseudo est déjà utilisé" : " et ce pseudo est déjà pris";
        }

        if(error.length() == 0){
            if(!customerManager.createCustomer(pseudo,prenom,nom,age,password)){
                error = "Impossible de créer votre compte";
                req.setAttribute("error", error);
                req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
            }else {
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        }else {
            req.setAttribute("error", error);
            req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
        }

    }

}
