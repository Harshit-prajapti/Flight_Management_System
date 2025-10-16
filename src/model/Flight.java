package model;

public class Flight {

    private int flightId;
    private String flightNumber;
    private String airlineName;
    private int seatsEconomy;
    private int seatsBusiness;
    private int seatsFirstClass;

    public Flight() {}

    public Flight(int flightId, String flightNumber, String airlineName,
                  int seatsEconomy, int seatsBusiness, int seatsFirstClass) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.airlineName = airlineName;
        this.seatsEconomy = seatsEconomy;
        this.seatsBusiness = seatsBusiness;
        this.seatsFirstClass = seatsFirstClass;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public int getSeatsEconomy() {
        return seatsEconomy;
    }

    public void setSeatsEconomy(int seatsEconomy) {
        this.seatsEconomy = seatsEconomy;
    }

    public int getSeatsBusiness() {
        return seatsBusiness;
    }

    public void setSeatsBusiness(int seatsBusiness) {
        this.seatsBusiness = seatsBusiness;
    }

    public int getSeatsFirstClass() {
        return seatsFirstClass;
    }

    public void setSeatsFirstClass(int seatsFirstClass) {
        this.seatsFirstClass = seatsFirstClass;
    }
}
