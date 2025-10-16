package project;

import java.sql.*;
import db.DatabaseConnection;
import ui.MainFrame;
public class Main {
	public static void main(String args[]) {
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
