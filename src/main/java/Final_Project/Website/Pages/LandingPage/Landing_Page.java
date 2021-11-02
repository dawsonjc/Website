package Final_Project.Website.Pages.LandingPage;

import Final_Project.Website.Object.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import Final_Project.Website.Object.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.*;

@Controller
public class Landing_Page {
    @GetMapping(value = "/locations")
    public String Table(Model model) {
        final String url = "jdbc:mysql://localhost:3306/clients";
        final String username = "root";
        final String password = "password";

        ArrayList<Location> locations = new ArrayList<Location>();
        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement query = conn.createStatement();
            String sql = "SELECT * FROM Locations"; // add this for final release - WHERE Status != 'Awaiting Approval';
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

    @GetMapping(value = "/locations/newLocation")
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
                    "VALUES('Awaiting Approval', ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, location.getLocation_Name());
            preparedStatement.setString(2, location.getLocation_Status());
            preparedStatement.setString(3, location.getAddress());
            preparedStatement.setString(4, location.getCity());
            preparedStatement.setString(5, location.getCountry());
            preparedStatement.setString(6, location.getZip());


            // insert_Into_Table(conn, sql, new String[], 1, 2, 4, 5, 6);
            // Google maps Api call to get lat and long with location.setLat and location.setLong

            // default lat_Long
            double[] lat_Long = new double[2];
            try {
                lat_Long = API.get_GeoCode(Location.encode_Address(location));
            }
            catch(IOException | InterruptedException e) {
                e.printStackTrace();
            }
            location.setLatitude(lat_Long[0]);
            location.setLongitude(lat_Long[1]);

            preparedStatement.setDouble(7, location.getLatitude());
            preparedStatement.setDouble(8, location.getLongitude());

            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return "redirect:/locations";
    }
}