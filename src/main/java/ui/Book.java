/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import model.Booking;
import model.Session;
import model.User;
import model.Passenger;

/**
 *
 * @author Dell
 */
public class Book extends javax.swing.JPanel {

    /**
     * Creates new form Book
     */
    private MainFrame mainframe;
    private int scheduleId;
    private ArrayList<Passenger> list = new ArrayList<Passenger>();
    private int totalSeats = 1;
    private String choosedClass = "First Class";
    private int economyAvailable;
    private int businessAvailable;
    private int firstAvailable;
    private int currentPassenger;

    public Book(MainFrame mainframe) {
        this.mainframe = mainframe;
        initComponents();
        bookNow.setVisible(false);
        jPanel2.setVisible(false);
        detailsPanel.setVisible(false);
    }

    public void setData(int scheduleId) {
        this.scheduleId = scheduleId;
        setAllData(scheduleId);
    }

    public void setAllData(int scheduleId) {
        String sql = """
        SELECT s.schedule_id, s.flight_id, f.flight_number, f.airline_name,f.seats_economy,f.seats_business,f.seats_first_class,
               s.from_location, s.to_location,
               s.departure_time, s.arrival_time,
               s.available_seats_economy, s.available_seats_business, s.available_seats_first_class
        FROM flightschedule s
        JOIN flight f ON s.flight_id = f.flight_id
        WHERE s.schedule_id = ? 
        ORDER BY s.departure_time ASC;
    """;

        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, scheduleId);

            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    // Set basic flight details
                    airline.setText(res.getString("airline_name"));
                    flight.setText(res.getString("flight_number"));
                    from.setText(res.getString("from_location"));
                    to.setText(res.getString("to_location"));
                    economy.setText(res.getString("available_seats_economy"));
                    business.setText(res.getString("available_seats_business"));
                    firstclass.setText(res.getString("available_seats_first_class"));

                    // Parse dates
                    java.sql.Timestamp depTs = res.getTimestamp("departure_time");
                    java.sql.Timestamp arrTs = res.getTimestamp("arrival_time");
                    dTime.setText(depTs.toString());
                    aTime.setText(arrTs.toString());
                    java.util.Date departureDate = new java.util.Date(depTs.getTime());
//                java.util.Date arrivalDate = new java.util.Date(arrTs.getTime());
                    java.util.Date bookingDate = new java.util.Date(); // current time

                    // Calculate duration in minutes
                    long durationMillis = arrTs.getTime() - depTs.getTime();
                    int durationMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(durationMillis);

                    // Parse available seats
                    economyAvailable = res.getInt("available_seats_economy");
                    businessAvailable = res.getInt("available_seats_business");
                    firstAvailable = res.getInt("available_seats_first_class");

                    // Assume total seats per class (adjust as needed)
                    int totalEconomy = res.getInt("seats_economy");
                    int totalBusiness = res.getInt("seats_business");
                    int totalFirst = res.getInt("seats_first_class");

                    // Calculate prices
                    double ePriceValue = Booking.calculateTicketPrice("economy", bookingDate, departureDate, totalEconomy, economyAvailable, durationMinutes);
                    double bPriceValue = Booking.calculateTicketPrice("business", bookingDate, departureDate, totalBusiness, businessAvailable, durationMinutes);
                    double fPriceValue = Booking.calculateTicketPrice("first", bookingDate, departureDate, totalFirst, firstAvailable, durationMinutes);

                    // Set prices (formatted to 2 decimal places)
                    eprice.setText(String.format("%.2f", ePriceValue));
                    bPrice.setText(String.format("%.2f", bPriceValue));
                    fPrice.setText(String.format("%.2f", fPriceValue));
                    User user = Session.getCurrentUser();
                    if (user == null) {
                        JOptionPane.showMessageDialog(null, "Please Login First", "Input Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    } else {
                        bookNow.setVisible(true);

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        airline = new javax.swing.JLabel();
        business = new javax.swing.JLabel();
        flight = new javax.swing.JLabel();
        firstclass = new javax.swing.JLabel();
        economy = new javax.swing.JLabel();
        from = new javax.swing.JLabel();
        to = new javax.swing.JLabel();
        eprice = new javax.swing.JLabel();
        bPrice = new javax.swing.JLabel();
        fPrice = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        login = new javax.swing.JButton();
        bookNow = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        dTime = new javax.swing.JLabel();
        aTime = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        clssess = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        totalBooking = new javax.swing.JTextField();
        next = new javax.swing.JButton();
        detailsPanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        gender = new javax.swing.JComboBox<>();
        age = new javax.swing.JTextField();
        passport = new javax.swing.JTextField();
        submit = new javax.swing.JButton();

        setBackground(new java.awt.Color(227, 242, 253));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(33, 97, 140));
        jLabel2.setText("Airline - ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(33, 97, 140));
        jLabel3.setText("Flight -");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(33, 97, 140));
        jLabel4.setText("From -");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(33, 97, 140));
        jLabel6.setText("To -");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(33, 97, 140));
        jLabel7.setText("Business -");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(33, 97, 140));
        jLabel8.setText("Economy - ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(33, 97, 140));
        jLabel9.setText(" First Class -");

        airline.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        airline.setText("Na");

        business.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        business.setText("Na");

        flight.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        flight.setText("Na");

        firstclass.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        firstclass.setText("Na");

        economy.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        economy.setText("Na");

        from.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        from.setText("Na");

        to.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        to.setText("Na");

        eprice.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        eprice.setText("Na");

        bPrice.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        bPrice.setText("Na");

        fPrice.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        fPrice.setText("Na");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("BOOK YOUR DREAM JOURNEY");

        login.setBackground(new java.awt.Color(0, 102, 102));
        login.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        login.setForeground(new java.awt.Color(255, 255, 255));
        login.setText("LOGIN");
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleLogin(evt);
            }
        });

        bookNow.setBackground(new java.awt.Color(0, 102, 102));
        bookNow.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bookNow.setForeground(new java.awt.Color(255, 255, 255));
        bookNow.setText("Book Now");
        bookNow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookNowhandleLogin(evt);
                bookNow(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(33, 97, 140));
        jLabel18.setText("Arrival Time");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(33, 97, 140));
        jLabel19.setText("Departure Time");

        dTime.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        dTime.setText("Na");

        aTime.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        aTime.setText("Na");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(login)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(308, 308, 308)
                .addComponent(bookNow)
                .addGap(42, 42, 42))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(airline, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(economy, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(eprice, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(flight, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(from, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(firstclass, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(fPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(109, 109, 109)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(bPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(63, 63, 63)
                                .addComponent(business, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(aTime, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(dTime, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(login)
                    .addComponent(bookNow))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(airline)
                    .addComponent(to)
                    .addComponent(from)
                    .addComponent(flight)
                    .addComponent(jLabel19)
                    .addComponent(dTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7)
                    .addComponent(economy)
                    .addComponent(firstclass)
                    .addComponent(business)
                    .addComponent(jLabel18)
                    .addComponent(aTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bPrice)
                    .addComponent(fPrice)
                    .addComponent(eprice))
                .addGap(22, 22, 22))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Enter Class");

        clssess.setBackground(new java.awt.Color(0, 88, 143));
        clssess.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        clssess.setForeground(new java.awt.Color(255, 255, 255));
        clssess.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "First Class", "Business", "Economy", " " }));
        clssess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clssessActionPerformed(evt);
            }
        });

        jLabel11.setText("Number of Bookings");

        totalBooking.setText("1");
        totalBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleChange(evt);
            }
        });

        next.setText("Next ->");
        next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleFly(evt);
            }
        });

        detailsPanel.setBackground(new java.awt.Color(242, 244, 246));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Enter The Details of Passenger");

        jLabel13.setText("Full Name");

        jLabel14.setText("Age");

        jLabel16.setText("Passport Number");

        jLabel15.setText("Choose Gender");

        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "male", "female", "other" }));

        submit.setText("Submit");
        submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hangleSubmit(evt);
            }
        });

        javax.swing.GroupLayout detailsPanelLayout = new javax.swing.GroupLayout(detailsPanel);
        detailsPanel.setLayout(detailsPanelLayout);
        detailsPanelLayout.setHorizontalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(389, 389, 389))
            .addGroup(detailsPanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(submit)
                    .addGroup(detailsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(passport, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        detailsPanelLayout.setVerticalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel12)
                .addGap(43, 43, 43)
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(passport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(submit)
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(detailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel5)
                .addGap(26, 26, 26)
                .addComponent(clssess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(196, 196, 196)
                .addComponent(jLabel11)
                .addGap(29, 29, 29)
                .addComponent(totalBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(next)
                .addGap(63, 63, 63))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(totalBooking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(next)
                    .addComponent(jLabel5)
                    .addComponent(clssess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(detailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void handleLogin(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_handleLogin
        // TODO add your handling code here:
        mainframe.moveToLogin();
    }//GEN-LAST:event_handleLogin

    private void bookNowhandleLogin(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookNowhandleLogin
        // TODO add your handling code here:
    }//GEN-LAST:event_bookNowhandleLogin

    private void bookNow(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookNow
        // TODO add your handling code here:
        jPanel2.setVisible(true);
    }//GEN-LAST:event_bookNow

    private void hangleSubmit(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hangleSubmit
        // TODO add your handling code here:
        getPassengerData();

        currentPassenger++;
        if (currentPassenger < totalSeats) {
            JOptionPane.showMessageDialog(null, "Enter details for passenger " + (currentPassenger + 1));
        } else {
            submit.setText("Book Now");
            finalizeBooking();
        }

    }//GEN-LAST:event_hangleSubmit

    public void getPassengerData() {
        String fullName = name.getText().trim();
        String gen = (String) gender.getSelectedItem();
        int old = Integer.parseInt(age.getText().trim());
        String pass = passport.getText().trim();

        String seatNumber;
        if (choosedClass.equals("First Class")) {
            seatNumber = String.valueOf(firstAvailable - list.size());
        } else if (choosedClass.equals("Business")) {
            seatNumber = String.valueOf(businessAvailable - list.size());
        } else {
            seatNumber = String.valueOf(economyAvailable - list.size());
        }

        Passenger passenger = new Passenger(-1, fullName, gen, old, pass, seatNumber);
        list.add(passenger);
        name.setText("");
        age.setText("");
        passport.setText("");
    }
    private void handleFly(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_handleFly
        // TODO add your handling code here:
        detailsPanel.setVisible(true);
        totalSeats = Integer.parseInt(totalBooking.getText());
        choosedClass = (String) clssess.getSelectedItem();
        currentPassenger = 0;
        list.clear();
        submit.setText("Next Passenger");
    }//GEN-LAST:event_handleFly

    private void finalizeBooking() {
        Connection con = null;
        PreparedStatement pstBooking = null;
        PreparedStatement pstPassenger = null;
        ResultSet rs = null;

        try {
            con = DatabaseConnection.getConnection();
            con.setAutoCommit(false); // Transaction start

            // Step 1: Insert booking record
            String bookingSQL = "INSERT INTO booking (user_id, schedule_id, seat_class, number_of_seats, total_price, payment_status) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            pstBooking = con.prepareStatement(bookingSQL, Statement.RETURN_GENERATED_KEYS);

            int userId = Session.getCurrentUser().getUserId();
            double totalPrice = calculateTotalPrice();

            pstBooking.setInt(1, userId);
            pstBooking.setInt(2, scheduleId);
            pstBooking.setString(3, choosedClass.toLowerCase().replace(" ", "_")); // converts “First Class” -> “first_class”
            pstBooking.setInt(4, totalSeats);
            pstBooking.setDouble(5, totalPrice);
            pstBooking.setString(6, "pending");

            pstBooking.executeUpdate();

            // Step 2: Get generated booking_id
            rs = pstBooking.getGeneratedKeys();
            int bookingId = -1;
            if (rs.next()) {
                bookingId = rs.getInt(1);
            }

            // Step 3: Insert passengers
            String passengerSQL = "INSERT INTO passenger (booking_id, full_name, gender, age, passport_number, seat_number) VALUES (?, ?, ?, ?, ?, ?)";
            pstPassenger = con.prepareStatement(passengerSQL);

            for (Passenger p : list) {
                pstPassenger.setInt(1, bookingId);
                pstPassenger.setString(2, p.getFullName());
                pstPassenger.setString(3, p.getGender());
                pstPassenger.setInt(4, p.getAge());
                pstPassenger.setString(5, p.getPassportNumber());
                pstPassenger.setString(6, p.getSeatNumber());
                pstPassenger.addBatch();
            }

            pstPassenger.executeBatch();

//            // Step 4: Update seat availability
//            String updateSQL = "";
//            if (choosedClass.equals("First Class")) {
//                updateSQL = "UPDATE flightschedule SET available_seats_first_class = available_seats_first_class - ? WHERE schedule_id = ?";
//            } else if (choosedClass.equals("Business")) {
//                updateSQL = "UPDATE flightschedule SET available_seats_business = available_seats_business - ? WHERE schedule_id = ?";
//            } else {
//                updateSQL = "UPDATE flightschedule SET available_seats_economy = available_seats_economy - ? WHERE schedule_id = ?";
//            }
//
//            try (PreparedStatement pstUpdate = con.prepareStatement(updateSQL)) {
//                pstUpdate.setInt(1, totalSeats);
//                pstUpdate.setInt(2, scheduleId);
//                pstUpdate.executeUpdate();
//            }

            // Step 5: Commit transaction
            con.commit();

            JOptionPane.showMessageDialog(this, "Booking successful! Booking ID: " + bookingId + " Now Make Panyment to Confirm Booking");
            list.clear();
            detailsPanel.setVisible(false);
            jPanel2.setVisible(false);
            mainframe.moveToBooking();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Booking failed! Please try again.");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstBooking != null) {
                    pstBooking.close();
                }
                if (pstPassenger != null) {
                    pstPassenger.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private double calculateTotalPrice() {
        double pricePerSeat;
        if (choosedClass.equals("First Class")) {
            pricePerSeat = Double.parseDouble(fPrice.getText());
        } else if (choosedClass.equals("Business")) {
            pricePerSeat = Double.parseDouble(bPrice.getText());
        } else {
            pricePerSeat = Double.parseDouble(eprice.getText());
        }
        return pricePerSeat * totalSeats;
    }

    private void handleChange(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_handleChange
        // TODO add your handling code here:


    }//GEN-LAST:event_handleChange

    private void clssessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clssessActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clssessActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aTime;
    private javax.swing.JTextField age;
    private javax.swing.JLabel airline;
    private javax.swing.JLabel bPrice;
    private javax.swing.JButton bookNow;
    private javax.swing.JLabel business;
    private javax.swing.JComboBox<String> clssess;
    private javax.swing.JLabel dTime;
    private javax.swing.JPanel detailsPanel;
    private javax.swing.JLabel economy;
    private javax.swing.JLabel eprice;
    private javax.swing.JLabel fPrice;
    private javax.swing.JLabel firstclass;
    private javax.swing.JLabel flight;
    private javax.swing.JLabel from;
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton login;
    private javax.swing.JTextField name;
    private javax.swing.JButton next;
    private javax.swing.JTextField passport;
    private javax.swing.JButton submit;
    private javax.swing.JLabel to;
    private javax.swing.JTextField totalBooking;
    // End of variables declaration//GEN-END:variables
}
