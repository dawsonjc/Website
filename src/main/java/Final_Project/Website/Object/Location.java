package Final_Project.Website.Object;
import java.awt.image.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Location {
    private int LocationId;

    // displayed info
    private String Status;
    private String Location_Name;
    private String Location_Status;

    // location
    private String Address;
    private String City;
    private String Country;
    private String Zip;
    private double Latitude;
    private double Longitude;

    public void setId(int locationId) {
        this.LocationId = locationId;
    }
    public void setStatus(String status) {
        this.Status = status;
    }
    public void setLocation_Name(String location_Name) {
        this.Location_Name = location_Name;
    }
    public void setLocation_Status(String location_Status) {
        this.Location_Status = location_Status;
    }
    public void setAddress(String address) {
        this.Address = address;
    }
    public void setCity(String city) {
        this.City = city;
    }
    public void setCountry(String country) {
        this.Country = country;
    }
    public void setZip(String zip) {
        this.Zip = zip;
    }
    public void setLatitude(double latitude) {
        this.Latitude = latitude;
    }
    public void setLongitude(double longitude) {
        this.Longitude = longitude;
    }

    public int getLocationId() {
        return this.LocationId;
    }
    public String getStatus() {
        return this.Status;
    }
    public String getLocation_Name() {
        return this.Location_Name;
    }
    public String getLocation_Status() {
        return this.Location_Status;
    }
    public String getAddress() {
        return this.Address;
    }
    public String getCity() {
        return this.City;
    }
    public String getCountry() {
        return this.Country;
    }
    public String getZip() {
        return this.Zip;
    }
    public double getLatitude() {
        return this.Latitude;
    }
    public double getLongitude() {
        return this.Longitude;
    }

    public static Location getLocationFromServer(ResultSet info) throws SQLException {
        Location location = new Location();
        location.Status = info.getString(2);
        location.Location_Name = info.getString(3);
        location.Location_Status = info.getString(4);
        location.Address = info.getString(5);
        location.City = info.getString(6);
        location.Country = info.getString(7);
        location.Zip = info.getString(8);
        location.Latitude = info.getFloat(9);
        location.Longitude = info.getFloat(10);
        return location;
    }

    public static String encode_Address(Location location) {
        String address = location.Address + " " + location.City + " " + location.Country;
        try {
            return URLEncoder.encode(address, StandardCharsets.UTF_8.toString());
        }
        catch(UnsupportedEncodingException e) {
            return "24%20Sussex%20Drive%20Ottawa%20ON";
        }
    }

    public static boolean Is_Location_Information_Filled(Location location) {
        // Check if any of the input values are either null or empty
        if(location.Location_Name == null || location.Location_Name.isEmpty()) {
            return false;
        }
        if(location.Location_Status == null || location.Location_Status.isEmpty()) {
            return false;
        }
        if(location.Address == null || location.Address.isEmpty()) {
            return false;
        }
        if(location.City == null || location.City.isEmpty()) {
            return false;
        }
        if(location.Country == null || location.Country.isEmpty()) {
            return false;
        }
        if(location.Zip == null || location.Zip.isEmpty()) {
            return false;
        }
        // Each value is not null or empty
        return true;
    }
}
