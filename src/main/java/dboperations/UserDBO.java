package dboperations;

import db.DatabaseConnection;
import model.User;

import java.sql.*;

public class UserDBO {

    public static boolean registerUser(User user) {
        String sql = "Insert into User (name,email,password,phone_number) values ('"+user.getName()+ "','" + user.getEmail() + "', '"+user.getPassword()+"' , '"+user.getPhoneNumber()+"');";
        System.out.println(sql);

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement()) {
           
            int res = st.executeUpdate(sql);
            if(res != 0) return true;
            else return false;
            
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User login(String email, String password) {
        String sql = "SELECT * FROM User WHERE email = '"+email+"' AND password = '"+password+"';";
        System.out.println(sql);

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setRole(rs.getString("role"));
                return user;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

