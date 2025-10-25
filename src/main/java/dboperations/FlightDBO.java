package dboperations;

import db.DatabaseConnection;
import model.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
public class FlightDBO {

    public static boolean addFlight(Flight flight) {
        String sql = "INSERT INTO Flight (flight_number, airline_name, seats_economy, seats_business, seats_first_class) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, flight.getFlightNumber());
            stmt.setString(2, flight.getAirlineName());
            stmt.setInt(3, flight.getSeatsEconomy());
            stmt.setInt(4, flight.getSeatsBusiness());
            stmt.setInt(5, flight.getSeatsFirstClass());

            return stmt.executeUpdate() > 0;

        } catch (ClassNotFoundException| SQLException  e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Flight> getAllFlights() {
        ArrayList<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM Flight";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Flight flight = new Flight();
                flight.setFlightId(rs.getInt("flight_id"));
                flight.setFlightNumber(rs.getString("flight_number"));
                flight.setAirlineName(rs.getString("airline_name"));
                flight.setSeatsEconomy(rs.getInt("seats_economy"));
                flight.setSeatsBusiness(rs.getInt("seats_business"));
                flight.setSeatsFirstClass(rs.getInt("seats_first_class"));

                flights.add(flight);
            }

        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    
    
    // You can add update and delete methods later if needed
}

