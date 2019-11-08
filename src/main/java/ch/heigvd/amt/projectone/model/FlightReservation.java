package ch.heigvd.amt.projectone.model;

import java.util.List;

import lombok.Getter;

@Getter
public class FlightReservation
{
    private Flight flight;
    private List<Customer> customers;

    public FlightReservation(Flight flight, List<Customer> customers) {
        this.flight = flight;
        this.customers = customers;
    }
}
