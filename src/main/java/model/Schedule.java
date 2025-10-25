/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
package model;

import db.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class Schedule {

    private int scheduleId;
    private int flightId;
    private String fromLocation;
    private String toLocation;
    private Timestamp departureTime;
    private Timestamp arrivalTime;
    private int availableSeatsEconomy;
    private int availableSeatsBusiness;
    private int availableSeatsFirstClass;

    // --- Constructors ---
    public Schedule() {}

    public Schedule(int scheduleId, int flightId, String fromLocation, String toLocation,
                    Timestamp departureTime, Timestamp arrivalTime,
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

    // --- Getters and Setters ---
    public int getScheduleId() { return scheduleId; }
    public void setScheduleId(int scheduleId) { this.scheduleId = scheduleId; }

    public int getFlightId() { return flightId; }
    public void setFlightId(int flightId) { this.flightId = flightId; }

    public String getFromLocation() { return fromLocation; }
    public void setFromLocation(String fromLocation) { this.fromLocation = fromLocation; }

    public String getToLocation() { return toLocation; }
    public void setToLocation(String toLocation) { this.toLocation = toLocation; }

    public Timestamp getDepartureTime() { return departureTime; }
    public void setDepartureTime(Timestamp departureTime) { this.departureTime = departureTime; }

    public Timestamp getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(Timestamp arrivalTime) { this.arrivalTime = arrivalTime; }

    public int getAvailableSeatsEconomy() { return availableSeatsEconomy; }
    public void setAvailableSeatsEconomy(int availableSeatsEconomy) { this.availableSeatsEconomy = availableSeatsEconomy; }

    public int getAvailableSeatsBusiness() { return availableSeatsBusiness; }
    public void setAvailableSeatsBusiness(int availableSeatsBusiness) { this.availableSeatsBusiness = availableSeatsBusiness; }

    public int getAvailableSeatsFirstClass() { return availableSeatsFirstClass; }
    public void setAvailableSeatsFirstClass(int availableSeatsFirstClass) { this.availableSeatsFirstClass = availableSeatsFirstClass; }

    // --- toString() (Optional, for debugging) ---
    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", flightId=" + flightId +
                ", from='" + fromLocation + '\'' +
                ", to='" + toLocation + '\'' +
                ", departure=" + departureTime +
                ", arrival=" + arrivalTime +
                ", economy=" + availableSeatsEconomy +
                ", business=" + availableSeatsBusiness +
                ", firstClass=" + availableSeatsFirstClass +
                '}';
    }

    // --- Database Search Method ---
    /**
     * Search for available flights by source, destination, and departure date.
     * @param conn Active JDBC Connection
     * @param from Source city name
     * @param to Destination city name
     * @param departureDate Date (yyyy-MM-dd) to search
     * @return List of matching Schedule objects
     */
    public static DefaultTableModel searchFlightsTableModel(String from, String to, java.sql.Date departureDate) {
    String[] columns = {"Schedule ID", "Flight No", "Airline", "From", "To", "Departure", "Arrival", "Eco Seats", "Bus Seats", "First Seats"};
    DefaultTableModel model = new DefaultTableModel(columns, 0);

    String sql = """
            SELECT s.schedule_id, s.flight_id, f.flight_number, f.airline_name,
                   s.from_location, s.to_location,
                   s.departure_time, s.arrival_time,
                   s.available_seats_economy, s.available_seats_business, s.available_seats_first_class
            FROM flightschedule s
            JOIN flight f ON s.flight_id = f.flight_id
            WHERE s.from_location = ? 
              AND s.to_location = ?
              AND DATE(s.departure_time) = ?
            ORDER BY s.departure_time ASC;
        """;

    try (Connection con = DatabaseConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setString(1, from);
        pst.setString(2, to);
        pst.setDate(3, departureDate);

        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("schedule_id"),
                    rs.getString("flight_number"),
                    rs.getString("airline_name"),
                    rs.getString("from_location"),
                    rs.getString("to_location"),
                    rs.getTimestamp("departure_time"),
                    rs.getTimestamp("arrival_time"),
                    rs.getInt("available_seats_economy"),
                    rs.getInt("available_seats_business"),
                    rs.getInt("available_seats_first_class")
                };
                model.addRow(row);
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return model;
}


    }


