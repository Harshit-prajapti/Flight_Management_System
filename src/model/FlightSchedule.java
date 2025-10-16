package model;

import java.time.LocalDateTime;

public class FlightSchedule {

    private int scheduleId;
    private int flightId;
    private String fromLocation;
    private String toLocation;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int availableSeatsEconomy;
    private int availableSeatsBusiness;
    private int availableSeatsFirstClass;

    public FlightSchedule() {}

    public FlightSchedule(int scheduleId, int flightId, String fromLocation, String toLocation,
                          LocalDateTime departureTime, LocalDateTime arrivalTime,
                          int availableSeatsEconomy, int availableSeatsBusiness, int availableSeatsFirstClass) {
        this.scheduleId = scheduleId;
        this.flightId = flightId;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeatsEconomy = availableSeatsEconomy;
        this.availableSeatsBusiness = availableSeatsBusiness;
        this.availableSeatsFirstClass = availableSeatsFirstClass;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getAvailableSeatsEconomy() {
        return availableSeatsEconomy;
    }

    public void setAvailableSeatsEconomy(int availableSeatsEconomy) {
        this.availableSeatsEconomy = availableSeatsEconomy;
    }

    public int getAvailableSeatsBusiness() {
        return availableSeatsBusiness;
    }

    public void setAvailableSeatsBusiness(int availableSeatsBusiness) {
        this.availableSeatsBusiness = availableSeatsBusiness;
    }

    public int getAvailableSeatsFirstClass() {
        return availableSeatsFirstClass;
    }

    public void setAvailableSeatsFirstClass(int availableSeatsFirstClass) {
        this.availableSeatsFirstClass = availableSeatsFirstClass;
    }
}
