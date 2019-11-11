package ch.heigvd.amt.projectone.dao;

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
public class FlightManager
{
    @Resource(lookup = "java:/jdbc/FlightCompany")
    private DataSource dataSource;

    public Flight getFlightById(long id)
    {
        Flight flight = null;
        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement sql = connection.prepareStatement("SELECT * FROM flight WHERE flight_id = ?");
            sql.setObject(1, id);

            ResultSet result = sql.executeQuery();

            while(result.next())
            {

                String name = result.getString("name");
                long departureTime = result.getLong("departure_time");
                long arrivalTime = result.getLong("arrival_time");
                String startPoint = result.getString("start_point");
                String endPoint = result.getString("end_point");
                int price = result.getInt("price");

                //To create timestamp convert, peut etre proposé deux getter dans le model ??
                flight = new Flight(id, name, departureTime, arrivalTime, startPoint, endPoint, price);

            }
            connection.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }

        return flight;
    }

    public List<Flight> getFlightByDeparture(String departure)
    {
        List<Flight> flights = null;
        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement sql = connection.prepareStatement("SELECT * FROM flight WHERE start_point = ?");
            sql.setObject(1, departure);

            ResultSet result = sql.executeQuery();

            while(result.next())
            {
                int id = result.getInt("flight_id");
                String name = result.getString("name");
                long departureTime = result.getLong("departure_time");
                long arrivalTime = result.getLong("arrival_time");
                String endPoint = result.getString("end_point");
                int price = result.getInt("price");

                //To create timestamp convert, peut etre proposé deux getter dans le model ??
                flights.add(new Flight(id, name, departureTime, arrivalTime, departure, endPoint, price));

            }
            connection.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }

        return flights;
    }

    public List<Flight> getFlightByDestination(String destination)
    {
        List<Flight> flights = null;
        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement sql = connection.prepareStatement("SELECT * FROM flight WHERE end_point = ?");
            sql.setObject(1, destination);

            ResultSet result = sql.executeQuery();

            while(result.next())
            {
                int id = result.getInt("flight_id");
                String name = result.getString("name");
                long departureTime = result.getLong("departure_time");
                long arrivalTime = result.getLong("arrival_time");
                String startPoint = result.getString("start_point");
                int price = result.getInt("price");

                //To create timestamp convert, peut etre proposé deux getter dans le model ??
                flights.add(new Flight(id, name, departureTime, arrivalTime, startPoint, destination, price));

            }
            connection.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }

        return flights;
    }

    public List<Flight> getFlightByDepartureAndDestination(String departure, String destination)
    {
        List<Flight> flights = null;
        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement sql = connection.prepareStatement("SELECT * FROM flight WHERE (start_point = ? AND end_point = ?)");
            sql.setObject(1, departure);
            sql.setObject(2, destination);


            ResultSet result = sql.executeQuery();

            while(result.next())
            {
                int id = result.getInt("flight_id");
                String name = result.getString("name");
                long departureTime = result.getLong("departure_time");
                long arrivalTime = result.getLong("arrival_time");
                int price = result.getInt("price");

                //To create timestamp convert, peut etre proposé deux getter dans le model ??
                flights.add(new Flight(id, name, departureTime, arrivalTime, departure, destination, price));

            }
            connection.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }

        return flights;
    }

    public boolean createFlight(Flight flight)
    {
        return createFlight(flight.getName(),flight.getDepartureTime(), flight.getArrivalTime(), flight.getStartPoint(), flight.getEndPoint(), flight.getPrice());
    }

    public boolean createFlight(String name, long departureTime, long arrivalTime, String startPoint, String endPoint, int price)
    {
        boolean success = false;
        int nbRow;
        try{
            Connection connection = dataSource.getConnection();

            PreparedStatement sql = connection.prepareStatement("INSERT INTO flight (name, departure_time, arrival_time, start_point, end_point, price) VALUES (?,?,?,?,?,?)");

            sql.setObject(1, name);
            sql.setObject(2, departureTime);
            sql.setObject(3, arrivalTime);
            sql.setObject(4, startPoint);
            sql.setObject(5, endPoint);
            sql.setObject(6, price);

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

    public boolean deleteFlight(long id)
    {
        boolean success = false;
        int nbRow;
        try{
            Connection connection = dataSource.getConnection();

            PreparedStatement sql = connection.prepareStatement("DELETE FROM flight WHERE flight_id = ?");

            sql.setLong(1, id);
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

    public boolean updateFlight(Flight flight)
    {
        boolean success = false;
        if(flight != null)
        {
            int nbRow;
            try{
                Connection connection = dataSource.getConnection();

                PreparedStatement sql = connection.prepareStatement("UPDATE Flight SET name = ?, departure_time = ?, arrival_time = ?, start_point = ?, end_point = ?, price = ? WHERE flight_id = ?");

                sql.setObject(1, flight.getName());
                sql.setObject(2, flight.getDepartureTime());
                sql.setObject(3, flight.getArrivalTime());
                sql.setObject(4, flight.getStartPoint());
                sql.setObject(5, flight.getEndPoint());
                sql.setObject(6, flight.getPrice());
                sql.setObject(7, flight.getFlight_id());

                nbRow = sql.executeUpdate();
                connection.close();

                if(nbRow > 0)//if row are updated, return true
                {
                    success = true;
                }
            }catch(SQLException ex) {
                ex.printStackTrace();
            }
        }
        return success;
    }


}
