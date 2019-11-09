package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.dao.FlightManager;
import ch.heigvd.amt.projectone.model.Flight;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="ReserveServlet",urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet{

    @EJB
    private FlightManager flightManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String departure = req.getParameter("departure");
        String destination = req.getParameter("destination");

        String error = "";

        List<Flight> flights = null;

        if((departure == null) && (destination == null)){
            error = "Entrez au moins un lieu de départ ou de destination";
        }else if(destination == null){
            flights = flightManager.getFlightByDeparture(departure);
        }else if(departure == null){
            flights = flightManager.getFlightByDestination(destination);
        }else {
            flights = flightManager.getFlightByDepartureAndDestination(departure,destination);
        }




    }

}
