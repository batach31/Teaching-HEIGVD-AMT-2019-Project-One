package ch.heigvd.amt.projectone.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FlightReservation
{
    private Flight flight;
    private List<Customer> customers;
}
