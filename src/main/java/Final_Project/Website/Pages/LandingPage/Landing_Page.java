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
        model.addAttribute("Locations", User.getUsersFromServer(WebsiteApplication.connection, "SELECT * FROM Users"));
        return "locations";
    }

    @GetMapping(value = "/Login")
    public String Login_Form(Model model) {
        User user = new User();
        model.addAttribute("User", user);
        return "index";
    }
}
