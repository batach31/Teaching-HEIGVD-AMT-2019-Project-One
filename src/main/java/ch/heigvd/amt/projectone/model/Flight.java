package main.java.ch.heigvd.amt.projectone.model;

public class Flight
{
    private long flight_id;
    private String name;
    private long departureTime;
    private long arrivalTime;
    private String startPoint;
    private String endPoint;
    private int price;

    public Flight(long flight_id, String name, long departureTime, long arrivalTime, String startPoint, String endPoint, int price)
    {
        this.flight_id = flight_id;
        this.name = name;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.price = price;
    }

    public long getFlight_id()
    {
        return flight_id;
    }

    public String getName()
    {
        return name;
    }

    public long getDepartureTime()
    {
        return departureTime;
    }

    public long getArrivalTime()
    {
        return arrivalTime;
    }

    public String getStartPoint()
    {
        return startPoint;
    }

    public String getEndPoint()
    {
        return endPoint;
    }

    public int getPrice()
    {
        return price;
    }
}
