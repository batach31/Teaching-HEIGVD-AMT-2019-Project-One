package ch.heigvd.amt.projectone.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Flight
{
    private long flight_id;
    private String name;
    private long departureTime;
    private long arrivalTime;
    private String startPoint;
    private String endPoint;
    private int price;

}
