package heigvd.amt.projectone.model;

import ch.heigvd.amt.projectone.model.Customer;
import org.junit.Test;

import static org.junit.Assert.*;


public class CustomerTest {

    @Test
    public void itShouldBePossibleToCreateCustomers() {
        Customer Miguel = new Customer(1,"endmon", "Miguel", "Gouveia", 24, "passNohash");
        assertNotNull(Miguel);
        assertEquals("Miguel", Miguel.getFirstname());
        assertEquals("endmon", Miguel.getCustomer_pseudo());
        assertEquals("Gouveia", Miguel.getLastname());
        assertEquals(24, Miguel.getAge());
        assertEquals("passNohash", Miguel.getCustomer_pw());
    }

}
