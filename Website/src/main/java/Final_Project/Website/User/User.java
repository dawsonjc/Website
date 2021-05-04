package Final_Project.Website.User;

public class User {
    private String First_Name;
    private String Last_Name;
    private String Password;
    private String Username;
    private String Email;
    public User() {}
    public User(String first_Name, String last_Name, String email, String username, String password) {
        this.First_Name = first_Name;
        this.Last_Name = last_Name;
        this.Email = email;
        this.Username = username;
        this.Password = password;
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
}
