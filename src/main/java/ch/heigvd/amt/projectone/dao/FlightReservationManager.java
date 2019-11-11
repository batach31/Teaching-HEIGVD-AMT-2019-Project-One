package ch.heigvd.amt.projectone.dao;

import ch.heigvd.amt.projectone.model.Customer;
import ch.heigvd.amt.projectone.model.Flight;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FlightReservationManager
{
    @Resource(lookup = "java:/jdbc/FlightCompany")
    private DataSource dataSource;

    public boolean makeReservation(FlightManager flightManager)
    {
        boolean success = false;
        int nbRow;
        try{
            Connection connection = dataSource.getConnection();

            PreparedStatement sql = connection.prepareStatement("INSERT INTO customer (customer_id, flight_id) VALUES (?,?)");

            sql.setString(1, flightManager.get);
            sql.setString(2, firstname);

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

    public boolean deleteReservation(FlightManager flightManager)
    {

    }

    public Flight listFlight(long id_customer)
    {

    }

    public Customer listCustomer(long id_flight)
    {

    }
}
