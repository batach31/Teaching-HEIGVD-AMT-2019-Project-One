package ch.heigvd.amt.projectone.dao;

import ch.heigvd.amt.projectone.model.Customer;
import ch.heigvd.amt.projectone.model.Flight;
import ch.heigvd.amt.projectone.model.FlightReservation;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightReservationManager
{
    @Resource(lookup = "java:/jdbc/FlightCompany")
    private DataSource dataSource;

    public boolean makeReservation(Flight flight, Customer customer)
    {
        boolean success = false;
        int nbRow;
        try{
            Connection connection = dataSource.getConnection();

            PreparedStatement sql = connection.prepareStatement("INSERT INTO customer (customer_id, flight_id) VALUES (?,?)");

            sql.setLong(1, customer.getCustomer_id());
            sql.setLong(2, flight.getFlight_id());

            nbRow = sql.executeUpdate();
            connection.close();

            if(nbRow > 0)//if row are created, return true
            {
                success = true;
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }

        return success;
    }

    public boolean deleteReservation(Customer customer)
    {
        boolean success = false;
        int nbRow;
        try{
            Connection connection = dataSource.getConnection();

            PreparedStatement sql = connection.prepareStatement("DELETE FROM flightreservation WHERE customer_id = ?");

            sql.setObject(1, customer.getCustomer_id());
            nbRow = sql.executeUpdate();
            connection.close();

            if(nbRow > 0)//if row are deleted, return true
            {
                success = true;
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return success;
    }

    public List<Flight> listFlight(Customer customer)
    {
        List<Flight> flights = new ArrayList<Flight>();
        try{
            Connection connection = dataSource.getConnection();

            PreparedStatement sql = connection.prepareStatement("SELECT * FROM flight INNER JOIN flightReservation ON flight.flight_id = flightReservation.flight_id WHERE customer_id = ?");
            sql.setLong(1, customer.getCustomer_id());

            ResultSet result = sql.executeQuery();
            while (result.next())
            {
                flights.add(new Flight(result.getInt("flight_id"),result.getString("name"),
                        result.getLong("departure_time"),result.getLong("arrival_time"),
                        result.getString("start_point"), result.getString("end_point"),
                        result.getInt("price")));
            }
            connection.close();

        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return flights;
    }

    public FlightReservation Reservation(Flight flight)
    {
        List<Customer> customers = new ArrayList<Customer>();
        FlightReservation reservation;
        try{
            Connection connection = dataSource.getConnection();

            PreparedStatement sql = connection.prepareStatement("SELECT * FROM customer INNER JOIN flightReservation ON customer.customer_id = flightReservation.customer_id WHERE flight_id = ?");
            sql.setLong(1, flight.getFlight_id());

            ResultSet result = sql.executeQuery();
            while (result.next())
            {
                customers.add(new Customer(result.getInt("customer_id"),result.getString("customer_pseudo"),
                        result.getString("firstname"),result.getString("lastname"),
                        result.getInt("age"), result.getString("customer_pw")));
            }

            connection.close();

        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        reservation = new FlightReservation(flight, customers);
        return reservation;
    }
}
