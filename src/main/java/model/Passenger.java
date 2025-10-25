/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dell
 */
public class Passenger {
    private int passengerId;
    private int bookingId;
    private String fullName;
    private String gender; // 'male', 'female', 'other'
    private Integer age;
    private String passportNumber;
    private String seatNumber;

    // Default constructor
    public Passenger() {}

    // Parameterized constructor
    public Passenger(int bookingId, String fullName, String gender, Integer age, String passportNumber, String seatNumber) {
        this.bookingId = bookingId;
        this.fullName = fullName;
        this.gender = gender;
        this.age = age;
        this.passportNumber = passportNumber;
        this.seatNumber = seatNumber;
    }

    // Getters and Setters
    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerId=" + passengerId +
                ", bookingId=" + bookingId +
                ", fullName='" + fullName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", passportNumber='" + passportNumber + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                '}';
    }
}
