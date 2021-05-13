package Final_Project.Website.Pages.LandingPage;

import Final_Project.Website.Object.Location;
import Final_Project.Website.WebsiteApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import Final_Project.Website.Object.User;
import java.sql.*;

@Controller
public class Landing_Page {

    @RequestMapping(value = "/")
    public String Table(Model model) {
        String url = "jdbc:mysql://localhost:3306/clients";
        String username = "root";
        String password = "password";
        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("Successful Connection");
            model.addAttribute("Locations", User.getUsersFromServer(conn, "SELECT * FROM Users"));
        } catch (SQLException e) {
            System.out.println("MySQL server has not been implemented yet");
        }

        return "locations";
    }

    @GetMapping(value = "/Login")
    public String Login_Form(Model model) {
        User user = new User();
        model.addAttribute("User", user);
        return "index";
    }
}
