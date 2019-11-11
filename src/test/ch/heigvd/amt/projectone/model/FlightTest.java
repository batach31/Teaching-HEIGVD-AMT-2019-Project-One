package heigvd.amt.projectone.model;

import ch.heigvd.amt.projectone.model.Flight;
import org.junit.Test;

import static org.junit.Assert.*;


public class FlightTest {

    @Test
    public void itShouldBePossibleToCreateFlight() {
        Flight Airbus = new Flight(1,"AF1234",1573484400 , 1573743600, "Bale", "Paris", 535);
        assertNotNull(Airbus);
        assertEquals("AF1234", Airbus.getName());
        assertEquals(1573484400, Airbus.getDepartureTime());
        assertEquals(1573743600, Airbus.getArrivalTime());
        assertEquals("Bale", Airbus.getStartPoint());
        assertEquals("Paris", Airbus.getEndPoint());
        assertEquals(535, Airbus.getPrice());
    }

}
