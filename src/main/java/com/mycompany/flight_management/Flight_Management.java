/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.flight_management;
//import com.mycompany.flight_management.ui.DashBoard;

import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import ui.MainFrame;

public class Flight_Management {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        try{
                Connection obj = DatabaseConnection.getConnection();
                System.out.println("Database connected successfully "+obj);
            }catch(SQLException | ClassNotFoundException e){
                e.printStackTrace();
            }
                MainFrame obj = new MainFrame();
                obj.setVisible(true);
		System.out.println("Hello world");
    }
}
