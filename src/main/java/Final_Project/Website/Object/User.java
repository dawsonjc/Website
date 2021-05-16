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

    public User() {}
    public User(int id, String first_Name, String last_Name, String password, String username, String email, String type) {
        this.Id = id;
        this.First_Name = first_Name;
        this.Last_Name = last_Name;
        this.Password = password;
        this.Username = username;
        this.Email = email;
        this.Type = type;
    }

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

    public static User getUserFromServer(ResultSet info) throws SQLException {
        User current_User = new User();
        current_User.Id = info.getInt(1);
        current_User.First_Name = info.getString(2);
        current_User.Last_Name = info.getString(3);
        current_User.Password = info.getString(4);
        current_User.Username = info.getString(5);
        current_User.Email = info.getString(6);
        current_User.Type = info.getString(7);
        return current_User;
    }

    public static boolean Is_User_Information_Filled(User user) throws SQLException {
        // Check if any of the input values are either null or empty
        if(user.First_Name == null || user.First_Name.isEmpty()) {
            return false;
        }
        if(user.Last_Name == null || user.Last_Name.isEmpty()) {
            return false;
        }
        if(user.Username == null || user.Username.isEmpty()) {
            return false;
        }
        if(user.Email == null || user.Email.isEmpty()) {
            return false;
        }
        if(user.Password == null || user.Password.isEmpty()) {
            return false;
        }
        // Each value is not null or empty
        return true;
    }
}