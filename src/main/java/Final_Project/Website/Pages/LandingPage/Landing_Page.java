package Final_Project.Website.Pages.LandingPage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import Final_Project.Website.Object.Location;
import java.util.ArrayList;
import java.sql.*;

@Controller
// @RequestMapping(value = "/locations")
public class Landing_Page {
    @GetMapping(value = "/locations")
    public String Table(Model model) {
        final String url = "jdbc:mysql://localhost:3306/clients";
        final String username = "root";
        final String password = "password";

        ArrayList<Location> locations = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement query = conn.createStatement();

            String sql = "SELECT * FROM Locations";
            ResultSet resultSet = query.executeQuery(sql);

            // loop through result set and put it in the ArrayList
            while(resultSet.next()) {
                locations.add(Location.getLocationFromServer(resultSet));
            }

            // add variable to page
            model.addAttribute("Locations", locations);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "locations";
    }

    @GetMapping(value = "locations/newLocation")
    public String Location_Form(Model model) {
        Location location = new Location();
        model.addAttribute("Location", location);
        return "New_Location";
    }
}
