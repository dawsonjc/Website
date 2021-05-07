package Final_Project.Website.User;

import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

public class User {
    private String First_Name;
    private String Last_Name;
    private String Password;
    private String Username;
    private String Email;

    public void setFirst_Name(String first_Name) {
        this.First_Name = first_Name;
    }
    public void setLast_Name(String last_Name) {
        this.Last_Name = last_Name;
    }
    public void setPassword(String password) {
        this.Password = password;
    }
    public void setUsername(String username) {
        this.Username = username;
    }
    public void setEmail(String email) {
        this.Email = email;
    }

    public String getFirst_Name() {
        return this.First_Name;
    }
    public String getLast_Name() {
        return this.Last_Name;
    }
    public String getPassword() {
        return this.Password;
    }
    public String getUsername() {
        return this.Username;
    }
    public String getEmail() {
        return this.Email;
    }

    public static ResultSet getUserFromServer(Connection conn, String SQL) throws SQLException {
        Statement statement = conn.createStatement();
        conn.close();
        return statement.executeQuery(SQL);
    }
}
