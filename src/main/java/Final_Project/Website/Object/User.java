package Final_Project.Website.Object;


import java.sql.*;

public class User {
    private int Id;
    private String First_Name;
    private String Last_Name;
    private String Password;
    private String Username;
    private String Email;
    private String Type;

    public void setId(int id) {
        this.Id = id;
    }
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
    public void setType(String type) {
        this.Type = type;
    }

    public int getId() {
        return this.Id;
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
    public String getType() {
        return this.Type;
    }

    public static ResultSet getUsersFromServer(Connection conn, String SQL) {
        try {
            Statement statement = conn.createStatement();
            return statement.executeQuery(SQL);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
