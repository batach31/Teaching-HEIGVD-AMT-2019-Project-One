package heigvd.amt.projectone.dao;

import ch.heigvd.amt.projectone.dao.CustomerManager;
import ch.heigvd.amt.projectone.model.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CustomerManagerTest {


    @Test
    public void itShouldBePossibleToGenerateCustomerInDB()
    {
        Customer Miguel = new Customer(1, "Ydmon", "Miguel", "Gouveia", 24, "pass123");
        CustomerManager customerManager = new CustomerManager();
        customerManager.createCustomer(Miguel);
        Customer test = customerManager.getCustomerByPseudo("Ydmon");
        assertNotNull(test);
        assertEquals(test.getFirstname(), Miguel.getFirstname());
    }
}
