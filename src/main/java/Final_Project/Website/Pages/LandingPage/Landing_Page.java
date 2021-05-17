package Final_Project.Website.Pages.LandingPage;

import Final_Project.Website.Object.User;
import Final_Project.Website.Pages.AccountPage.AccountPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import Final_Project.Website.Object.Location;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.sql.*;

@Controller
// @RequestMapping(value = "/locations")
public class Landing_Page {
    private static User user = AccountPage.get_Current_User();

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

            // this is a really bad way, need to improve
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

    @PostMapping(value = "/locations/newLocation/submitLocation")
    public String Add_Location(@ModelAttribute("Location") Location location) {
        if(!Location.Is_Location_Information_Filled(location)) {
            return "redirect:/locations/newLocation";
        }

        final String URL = "jdbc:mysql://localhost:3306/clients";
        final String USERNAME = "root";
        final String PASSWORD = "password";

        // connect to database
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO locations(Status, Location_Name, Location_Status, Address, City, Country, Zip, Latitude, Longitude) " +
                    "VALUES(\"Awaiting Approval\", ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, location.getLocation_Name());
            preparedStatement.setString(2, location.getLocation_Status());
            preparedStatement.setString(3, location.getAddress());
            preparedStatement.setString(4, location.getCity());
            preparedStatement.setString(5, location.getCountry());
            preparedStatement.setString(6, location.getZip());


            // Google maps Api call to get lat and long with location.setLat and location.setLong
            String api = "https://maps.googleapis.com/maps/api/geocode/json?address=" + location.getAddress();
            URL url = new URL(api);



            preparedStatement.setFloat(7, location.getLatitude());
            preparedStatement.setFloat(8, location.getLongitude());



        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        catch(MalformedURLException e) {
            e.printStackTrace();
        }

        return "redirect:/locations";
    }
}