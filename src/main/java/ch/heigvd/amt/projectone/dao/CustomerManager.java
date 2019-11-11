package ch.heigvd.amt.projectone.dao;

import ch.heigvd.amt.projectone.business.IAuthenticationService;
import ch.heigvd.amt.projectone.model.Customer;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Stateless
public class CustomerManager
{
    @Resource(lookup = "java:/jdbc/FlightCompany")
    private DataSource dataSource;

    @EJB
    IAuthenticationService authenticationService;

    public Customer getCustomerById(long id)
    {
        Connection connection = null;
        Customer customer = null;
        try {
            connection = dataSource.getConnection();

            PreparedStatement sql = connection.prepareStatement("SELECT * FROM customer WHERE customer_id = ?");
            sql.setObject(1, id);

            ResultSet result = sql.executeQuery();

            while(result.next())
            {

                String pseudo = result.getString("customer_pseudo");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                int age = result.getInt("age");
                String passwd = result.getString("customer_pw");

                customer = new Customer(id, pseudo, firstname, lastname, age, passwd);

            }
            connection.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }finally {
            endConnection(connection);
        }

        return customer;
    }

    public boolean createCustomer(Customer customer)
    {
        return createCustomer(customer.getCustomer_pseudo(),customer.getFirstname(), customer.getLastname(), customer.getAge(), customer.getCustomer_pw());
    }

    public boolean createCustomer(String pseudo, String firstname, String lastname, int age, String passwd)
    {
        boolean success = false;
        int nbRow;
        Connection connection = null;
        try{
            connection = dataSource.getConnection();

            PreparedStatement sql = connection.prepareStatement("INSERT INTO customer (customer_pseudo, firstname, lastname, age, customer_pw) VALUES (?,?,?,?,?)");

            sql.setString(1, pseudo);
            sql.setString(2, firstname);
            sql.setString(3, lastname);
            sql.setInt(4, age);
            String password = authenticationService.hashPassword(passwd);
            sql.setString(5, password);

            nbRow = sql.executeUpdate();
            connection.close();

            if(nbRow > 0)//if row are created, return true
            {
                success = true;
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }finally {
            endConnection(connection);
        }

        return success;
    }

    public boolean deleteCustomer(long id)
    {
        boolean success = false;
        int nbRow;
        try{
            Connection connection = dataSource.getConnection();

            PreparedStatement sql = connection.prepareStatement("DELETE FROM customer WHERE customer_id = ?");

            sql.setObject(1, id);
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

    public boolean updateCustomer(Customer customer)
    {
        boolean success = false;
        if(customer != null)
        {
            int nbRow;
            try{
                Connection connection = dataSource.getConnection();

                PreparedStatement sql = connection.prepareStatement("UPDATE Customer SET customer_pseudo = ?, firstname = ?, lastname = ?, age = ?, customer_pw = ? WHERE customer_id = ?");

                sql.setObject(1, customer.getCustomer_pseudo());
                sql.setObject(2, customer.getFirstname());
                sql.setObject(3, customer.getLastname());
                sql.setObject(4, customer.getAge());
                sql.setObject(5, customer.getCustomer_pw());
                sql.setObject(6, customer.getCustomer_id());

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

    public Customer getCustomerByPseudo(String pseudo){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM customer WHERE customer_pseudo = ?");

            sql.setString(1, pseudo);

            ResultSet result = sql.executeQuery();


            if(result.next()){
                int id = result.getInt(1);
                String firstname = result.getString(3);
                String lastname = result.getString(4);
                int age = result.getInt(5);
                String passwd = result.getString(6);

                connection.close();

                return new Customer(id, pseudo, firstname, lastname, age, passwd);
            }


        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            endConnection(connection);
        }
        return null;
    }

    public boolean verifyPassword(String username, String password){
        boolean success = false;
        Connection connection = null;
        try{
            connection = dataSource.getConnection();

            PreparedStatement sql = connection.prepareStatement("SELECT customer_pw FROM customer WHERE customer_pseudo = ?");

            sql.setObject(1,username);

            ResultSet result = sql.executeQuery();

            if(result.wasNull()){
                return false;
            } else {
                result.next();
                String pwHash = result.getString("customer_pw");
                success = authenticationService.checkPassword(password,pwHash);
            }

            connection.close();
        }catch(SQLException ex) {
            ex.printStackTrace();
        }finally {
            endConnection(connection);
        }
        return success;
    }


    private void endConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
