/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dboperations;

import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Flight;

/**
 *
 * @author Dell
 */
public class ScheduleDBO {

    public static boolean addFlightSchedule(int flightId, String source, String destination,
            Timestamp arrivalTimeDate, Timestamp departureTimeDate, Flight flight) {

        // 1. Check: Departure time must be in the future
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (departureTimeDate.before(now)) {
            System.err.println("Error: Cannot schedule a flight in the past.");
            JOptionPane.showMessageDialog(null, " Cannot schedule a flight in the past.");
            return false;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {

            // 2. Check: Duplicate schedule (same flight at same departure time)
            String checkDuplicate = """
    SELECT COUNT(*) FROM flightschedule 
    WHERE flight_id = ? 
      AND (
        (? BETWEEN departure_time AND arrival_time)
        OR
        (? BETWEEN departure_time AND arrival_time)
        OR
        (departure_time BETWEEN ? AND ?)
      )
""";
            try (PreparedStatement pst = conn.prepareStatement(checkDuplicate)) {
                pst.setInt(1, flightId);
                pst.setTimestamp(2, departureTimeDate);  // new departure
                pst.setTimestamp(3, arrivalTimeDate);    // new arrival
                pst.setTimestamp(4, departureTimeDate);  // new departure again
                pst.setTimestamp(5, arrivalTimeDate);
                ResultSet rs = pst.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    System.err.println("Error: This flight is already scheduled between departure and arrival time");
                    JOptionPane.showMessageDialog(null, "This flight is already scheduled between departure and arrival time");
                    return false;
                }
            }

            // 3. Check: Continuity - last destination should match new source
            String checkLastLocation = """
            SELECT to_location FROM flightschedule 
            WHERE flight_id = ? 
            ORDER BY arrival_time DESC 
            LIMIT 1
        """;

            try (PreparedStatement pst = conn.prepareStatement(checkLastLocation)) {
                pst.setInt(1, flightId);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    String lastArrival = rs.getString("to_location");
                    System.out.println(lastArrival);
                    System.out.println(source);
                    if (!lastArrival.equalsIgnoreCase(source)) {
                        System.err.println("Error: This flight's last destination was '" + lastArrival
                                + "'. It must start the next journey from there.");
                        JOptionPane.showMessageDialog(null, "This flight's last destination was '" + lastArrival
                                + "'. It must start the next journey from there.");
                        return false;
                    }
                }
            }

            // All checks passed → Proceed with insert
            String sql = """
            INSERT INTO flightschedule 
            (flight_id, from_location, to_location, departure_time, arrival_time,
             available_seats_economy, available_seats_business, available_seats_first_class) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, flightId);
                stmt.setString(2, source);
                stmt.setString(3, destination);
                stmt.setTimestamp(4, departureTimeDate);
                stmt.setTimestamp(5, arrivalTimeDate);
                stmt.setInt(6, flight.getSeatsEconomy());
                stmt.setInt(7, flight.getSeatsBusiness());
                stmt.setInt(8, flight.getSeatsFirstClass());

                return stmt.executeUpdate() > 0;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

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
              AND s.departure_time >= ? AND s.departure_time < ?
            ORDER BY s.departure_time ASC;
        """;

        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, from);
            pst.setString(2, to);

            // Start of the day: 00:00:00
            Timestamp startOfDay = Timestamp.valueOf(departureDate.toLocalDate().atStartOfDay());
            // End of the day: 23:59:59.999
            Timestamp endOfDay = Timestamp.valueOf(departureDate.toLocalDate().plusDays(1).atStartOfDay());

            pst.setTimestamp(3, startOfDay);
            pst.setTimestamp(4, endOfDay);

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
