package heigvd.amt.projectone.model;


import ch.heigvd.amt.projectone.model.Flight;
import ch.heigvd.amt.projectone.model.FlightReservation;
import ch.heigvd.amt.projectone.model.Customer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FlightReservationTest {

    @Test
    public void itShouldBePossibleToCreateFlightReservation() {
        List<Customer> customers = new ArrayList<>();
        FlightReservation flightReservation = new FlightReservation(new Flight(1,"",1,1,"","",1),customers);
        assertNotNull(flightReservation);
    }

}
