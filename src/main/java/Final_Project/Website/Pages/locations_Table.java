package Final_Project.Website.Pages;

import Final_Project.Website.Object.Location;
import java.util.ArrayList;
import java.sql.*;
public class locations_Table extends Thread implements Runnable {
    private static final ArrayList<Location> locations = new ArrayList<>();

    public locations_Table() {
    }
    @Override
    public void run() {
        final String url = "jdbc:mysql://localhost:3306/clients";
        final String username = "root";
        final String password = "password";

        // connect to database
        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement query = conn.createStatement();
            String sql = "SELECT * FROM Locations";
            ResultSet resultSet = query.executeQuery(sql);

            // loop through result set and put it in the ArrayList

            // this is a really bad way, need to improve
            while(resultSet.next()) {
                locations.add(Location.getLocationFromServer(resultSet));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}

