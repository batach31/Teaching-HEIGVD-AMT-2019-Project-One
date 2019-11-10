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

@WebServlet(name="HomeServlet",urlPatterns = {"/home", "/home/*"})
public class HomeServlet extends HttpServlet{

    @EJB
    private FlightManager flightManager;

    private List<Flight> flights = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currPage = 0;
        int page_no = 1;
        int maxFilghtsPerPage = 8;
        int numberOfFlights = flights.size();

        if(req.getParameter("page_no") != null) {
            currPage = Integer.parseInt(req.getParameter("page_no"));
        }

        if(numberOfFlights > 0) {
            List<Flight> flightsOnPage = null;
            for(int i = 0; i < maxFilghtsPerPage; i++){
                flightsOnPage.add(flights.get((page_no - 1)*maxFilghtsPerPage));
            }

            req.setAttribute("flights", flightsOnPage);
            req.setAttribute("page_no", currPage);
            req.setAttribute("numberOfFlights", numberOfFlights);
        }

        req.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String departure = req.getParameter("departure");
        String destination = req.getParameter("destination");

        String error = "";

        if((departure == null) && (destination == null)){
            error = "Entrez au moins un lieu de départ ou de destination";
        }else if(destination == null){
            flights = flightManager.getFlightByDeparture(departure);
        }else if(departure == null){
            flights = flightManager.getFlightByDestination(destination);
        }else {
            flights = flightManager.getFlightByDepartureAndDestination(departure,destination);
        }

        resp.sendRedirect(req.getContextPath() + "/home/flights?pageNum=" + req.getParameter("currPage"));
    }

}
