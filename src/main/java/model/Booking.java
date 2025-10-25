package model;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Booking {
    
    // --- Base Prices per class (₹) ---
    public static final double ECONOMY_BASE = 5000.0;
    public static final double BUSINESS_BASE = 12000.0;
    public static final double FIRST_BASE = 20000.0;

    // --- Fixed Charges ---
    public static final double ENVIRONMENT_COST = 150.0;
    public static final double AIRPORT_CHARGE = 350.0;
    public static final double GST_PERCENT = 0.18; // 18

    private int bookingId;
    private int userId;
    private int scheduleId;
    private LocalDateTime bookingDate;
    private String seatClass; // economy, business, first_class
    private int numberOfSeats;
    private double totalPrice;
    private String paymentStatus; // paid, pending, cancelled

    public Booking() {}

    public Booking(int bookingId, int userId, int scheduleId, LocalDateTime bookingDate,
                   String seatClass, int numberOfSeats, double totalPrice, String paymentStatus) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.bookingDate = bookingDate;
        this.seatClass = seatClass;
        this.numberOfSeats = numberOfSeats;
        this.totalPrice = totalPrice;
        this.paymentStatus = paymentStatus;
    }

    public static double calculateTicketPrice( String cabinClass, Date bookingDate,Date departureDate,int totalSeats,int availableSeats,int durationMinutes) {

        double basePrice;
        // --- Base price by class ---
        switch (cabinClass.toLowerCase()) {
            case "business" -> basePrice = BUSINESS_BASE;
            case "first" -> basePrice = FIRST_BASE;
            default -> basePrice = ECONOMY_BASE;
        }

        // --- 1. Time Factor (closer booking = higher) ---
        long diffInMs = departureDate.getTime() - bookingDate.getTime();
        long diffDays = TimeUnit.DAYS.convert(diffInMs, TimeUnit.MILLISECONDS);

        double timeFactor;
        if (diffDays >= 30) timeFactor = 1.0;
        else if (diffDays >= 15) timeFactor = 1.1;
        else if (diffDays >= 7) timeFactor = 1.25;
        else if (diffDays >= 3) timeFactor = 1.4;
        else timeFactor = 1.6;

        // --- 2. Demand Factor (seat occupancy effect) ---
        double seatOccupancy = (double) (totalSeats - availableSeats) / totalSeats;
        double demandFactor = 1.0 + (seatOccupancy * 0.5);  // max +50%

        // --- 3. Duration Factor ---
        // Normalize flight duration into multipliers:
        // Short (<1hr) → 1.0, Medium (1–3hr) → 1.2, Long (>3hr) → 1.4, Ultra-long (>6hr) → 1.6
        double durationFactor;
        if (durationMinutes <= 60) durationFactor = 1.0;
        else if (durationMinutes <= 180) durationFactor = 1.2;
        else if (durationMinutes <= 360) durationFactor = 1.4;
        else durationFactor = 1.6;

        // --- 4. Calculate base dynamic price ---
        double ticketPrice = basePrice * timeFactor * demandFactor * durationFactor;

        // --- 5. Add fixed operational costs ---
        ticketPrice += ENVIRONMENT_COST + AIRPORT_CHARGE;

        // --- 6. Apply GST ---
        ticketPrice += ticketPrice * GST_PERCENT;

        return ticketPrice;
    }

    // --- Quick test demo ---
    
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
