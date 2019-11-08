package main.java.ch.heigvd.amt.projectone.model;

import java.util.List;

public class FlightReservation
{
    private Flight flight;
    private List<Customer> customers;

    public FlightReservation(Flight flight, List<Customer> customers) {
        this.flight = flight;
        this.customers = customers;
    }


    public Flight getFlight() {
        return flight;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
