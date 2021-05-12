package Final_Project.Website.Object;
import java.awt.image.*;
public class Location {
    private int LocationId;
    private String Location_Name;
    private String Status;
    private Double Latitude;
    private Double Longitude;
    public void Set_Id(int locationId) {
        this.LocationId = locationId;
    }
    public void Set_Location_name(String location_Name) {
        this.Location_Name = location_Name;
    }
    public void Set_Status(String status) {
        this.Status = status;
    }
    public void Set_Latitude(Double latitude) {
        this.Latitude = latitude;
    }
    public void Set_Longitude(Double longitude) {
        this.Longitude = longitude;
    }

    public int Get_LocationId() {
        return this.LocationId;
    }
    public String Get_Location_Name() {
        return this.Location_Name;
    }
    public String Get_Status() {
        return this.Status;
    }
    public Double Get_Latitude() {
        return this.Latitude;
    }
    public Double Get_Longitude() {
        return this.Longitude;
    }
}
