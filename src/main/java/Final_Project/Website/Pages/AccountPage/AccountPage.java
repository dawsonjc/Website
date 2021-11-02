package Final_Project.Website.Pages.AccountPage;

import Final_Project.Website.Object.User;
import Final_Project.Website.WebsiteApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.sql.*;
import java.util.Arrays;

@Controller
public class AccountPage {
    private User user = new User();
    @RequestMapping(value = "/Account")
    public String Account(Model model) {
        // fix
        if(false) {
            return "redirect:/Account/login";
        }
        System.out.println();
        model.addAttribute("Current_User", this.user);
        model.addAttribute("Update_User", new User());
        return "Account";
    }
    @PostMapping(value = "/Account/updateUser")
    public String Update_User(@ModelAttribute("Update_User") User updated_User) {
        if(!User.Is_User_Information_Filled(updated_User)) {
            return "redirect:/Account";
        }
        final String url = "jdbc:mysql://localhost:3306/clients";
        final String username = "root";
        final String password = "password";

        // connect to database
        try(Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "UPDATE client SET First_Name = ?, Last_Name = ?, Password = ?, Username = ?, Email = ? WHERE Id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, updated_User.getFirst_Name());
            ps.setString(2, updated_User.getLast_Name());
            ps.setString(3, updated_User.getPassword());
            ps.setString(4, updated_User.getUsername());
            ps.setString(5, updated_User.getEmail());
            ps.setInt(6, this.user.getId());

            ps.execute();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return "redirect:/locations/";
    }

    @GetMapping(value = "/Account/login")
    public String Login_Form(Model model) {
        if(this.user != null) {
            return "redirect:/locations/";
        }
        User UserLogin = new User();
        model.addAttribute("User", UserLogin);
        return "Login";
    }

    @PostMapping(value="/Account/user")
    public String Log_In(@ModelAttribute("User") User user1) {
        final String url = "jdbc:mysql://localhost:3306/clients";
        final String username = "root";
        final String password = "password";

        // connect to database
        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement query = conn.createStatement();

            // does the user exist?
            String sql = "SELECT COUNT(*) FROM client WHERE Username = ? AND Password = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user1.getUsername());
            ps.setString(2, user1.getPassword());
            ResultSet rs = ps.executeQuery();

            rs.next();
            if(rs.getInt(1) == 0) {
                return "redirect:/Account/login";
            }

            // user exists and now to link user to user object
            sql = "SELECT * FROM client WHERE Username = '" + user1.getUsername() + "' AND Password = '" + user1.getPassword() + "';";
            ResultSet user_Info = query.executeQuery(sql);
            if(user_Info.next()) {
                this.user = User.getUserFromServer(user_Info);
            }
            return "redirect:/locations";
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        //javax.servlet.http.Cookie cookie = new Cookie("why", "tell me why");
    }

    @GetMapping(value="/Account/signUp")
    public String signUpPage(Model model) {
        if(user != null) {
            return "redirect:/locations/";
        }
        User UserLogin = new User();
        model.addAttribute("User", UserLogin);
        return "SignUp";
    }

    @PostMapping(value = "Account/signUp/newUser")
    public String NewUser(@ModelAttribute(value = "User") User user1) {
        if(!User.Is_User_Information_Filled(user1)) {
            return "redirect:/Account/signUp";
        }

        final String url = "jdbc:mysql://localhost:3306/clients";
        final String username = "root";
        final String password = "password";

        // connect to database
        try(Connection conn = DriverManager.getConnection(url, username, password)) {

            // insert new user into database
            String sql = "INSERT INTO client (First_Name, Last_Name, Password, Username, Email, Type) " +
                    "VALUES(?, ?, ?, ?, ?, 'User');";
            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1, user1.getFirst_Name());
            query.setString(2, user1.getLast_Name());
            query.setString(3, user1.getPassword());
            query.setString(4, user1.getUsername());
            query.setString(5, user1.getEmail());
            query.execute();


            // set id to user
            sql = "SELECT * FROM client WHERE Username = ? AND Password = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user1.getUsername());
            ps.setString(2, user1.getPassword());
            ps.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return "redirect:/locations/";
    }
}
