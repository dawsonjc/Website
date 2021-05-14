package Final_Project.Website.Object;
import java.awt.image.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Location {
    private int LocationId;
    private String Location_Name;
    private String Status;
    private String Location_Status;
    private float Latitude;
    private float Longitude;
    public void setId(int locationId) {
        this.LocationId = locationId;
    }
    public void setLocation_name(String location_Name) {
        this.Location_Name = location_Name;
    }
    public void setStatus(String status) {
        this.Status = status;
    }
    public void setLocation_Status(String location_Status) {
        this.Location_Status = location_Status;
    }
    public void setLatitude(float latitude) {
        this.Latitude = latitude;
    }
    public void setLongitude(float longitude) {
        this.Longitude = longitude;
    }

    public int getLocationId() {
        return this.LocationId;
    }
    public String getLocation_Name() {
        return this.Location_Name;
    }
    public String getStatus() {
        return this.Status;
    }
    public String getLocation_Status() {
        return this.Location_Status;
    }
    public float getLatitude() {
        return this.Latitude;
    }
    public float getLongitude() {
        return this.Longitude;
    }

    public static Location getLocationFromServer(ResultSet info) throws SQLException {
        Location current_User = new Location();
        current_User.Location_Name = info.getString(2);
        current_User.Status = info.getString(3);
        current_User.Location_Status = info.getString(4);
        current_User.Latitude = info.getFloat(5);
        current_User.Longitude = info.getFloat(6);
        return current_User;
    }
}
