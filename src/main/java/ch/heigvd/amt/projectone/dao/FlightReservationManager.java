package ch.heigvd.amt.projectone.dao;

import ch.heigvd.amt.projectone.model.Customer;
import ch.heigvd.amt.projectone.model.Flight;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Stateless
public class FlightReservationManager
{
    @Resource(lookup = "java:/jdbc/FlightCompany")
    private DataSource dataSource;

    public List<Flight> getCustomerFlights(Customer customer){
        List<Flight> flights = null;
        FlightManager flightManager = new FlightManager();

        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement sql = connection.prepareStatement("SELECT flight_id FROM flightReservation WHERE customer_id = ?");
            sql.setObject(1, customer.getCustomer_id());

            ResultSet result = sql.executeQuery();

            while(result.next()){
                flights.add(flightManager.getFlightById(result.getLong("flight_id")));
            }
            connection.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }

        return flights;

    }

    public boolean addCustomerFlight(Long customer_id, Long flight_id){

        boolean success = false;
        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement sql = connection.prepareStatement("INSERT INTO flightReservation (customer_id, flight_id) VALUES (?,?)");
            sql.setObject(1, customer_id);
            sql.setObject(2, flight_id);


            ResultSet result = sql.executeQuery();


            int nbRow = sql.executeUpdate();
            connection.close();

            if(nbRow > 0)//if row are created, return true
            {
                success = true;
            }
            connection.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }

        return success;

    }

}
