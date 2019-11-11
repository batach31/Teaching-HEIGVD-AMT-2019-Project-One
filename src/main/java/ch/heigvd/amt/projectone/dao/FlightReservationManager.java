package ch.heigvd.amt.projectone.dao;

import ch.heigvd.amt.projectone.model.Customer;
import ch.heigvd.amt.projectone.model.Flight;
import ch.heigvd.amt.projectone.model.FlightReservation;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public Flight listFlight(long id_customer)
    {

    }

    public Customer listCustomer(long id_flight)
    {

    }
}
