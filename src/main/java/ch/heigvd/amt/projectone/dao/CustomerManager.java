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
        Customer customer = null;
        try {
            Connection connection = dataSource.getConnection();

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
        try{
            Connection connection = dataSource.getConnection();

            PreparedStatement sql = connection.prepareStatement("INSERT INTO customer (customer_pseudo, firstname, lastname, age, customer_pw) VALUES (?,?,?,?,?)");

            sql.setString(1, pseudo);
            sql.setString(2, firstname);
            sql.setString(3, lastname);
            sql.setInt(4, age);
            String password = authenticationService.hashPassword(passwd);
            sql.setString(5, password);

            success = sql.execute();
            connection.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }finally {
            return success;
        }

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
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM customer WHERE customer_pseudo = ?");

            sql.setObject(1, pseudo);

            ResultSet result = sql.executeQuery();

            result.next();

            int id = result.getInt("customer_id");
            String firstname = result.getString("firstname");
            String lastname = result.getString("lastname");
            int age = result.getInt("age");
            String passwd = result.getString("customer_pw");

            connection.close();

            return new Customer(id, pseudo, firstname, lastname, age, passwd);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public boolean verifyPassword(String username, String password){
        try{
            Connection connection = dataSource.getConnection();

            PreparedStatement sql = connection.prepareStatement("SELECT * FROM customer WHERE customer_pseudo = ?");

            sql.setObject(1,username);

            ResultSet result = sql.executeQuery();
            if(result.next())
            {
                String pwHash = result.getString("customer_pw");
                return authenticationService.checkPassword(password,pwHash);
            }

            connection.close();
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
