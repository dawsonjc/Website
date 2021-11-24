package Final_Project.Website.Pages.LandingPage;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class API {
    public static double[] get_GeoCode(String Address) throws IOException, InterruptedException {
        String Encoded_Address = URLEncoder.encode(Address, StandardCharsets.UTF_8);
        final String key = "AIzaSyBCyZlciOj_r_xJJ7Y3SeaIHvo5-yvXs-4";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://maps.googleapis.com/maps/api/geocode/json?language=en&key=" + key + "&address=" + Encoded_Address))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject google_Maps_Json = new JSONObject(response.body());
        JSONArray results = google_Maps_Json.getJSONArray("results");
        JSONObject first_Element = results.getJSONObject(0);
        JSONObject geometry = first_Element.getJSONObject("geometry");
        JSONObject location = geometry.getJSONObject("location");

        double lat = location.getFloat("lat");
        double Lng = location.getFloat("lng");

        return new double[] { lat, Lng };
    }

    /**
     * @param preparedStatement
     * the statement
     *
     * @param indices
     * The positions in our query where we will be inserting
     *
     * @param inserts
     * the things we are inserting
     * */
    public static void insert_Into_Table(PreparedStatement preparedStatement, Object[] inserts, int... indices) throws SQLException {
        if(inserts.length != indices.length) {
            throw new SQLException("inserts length do not match indices length");
        }

        int insert = 0;
        for(int index : indices) {
            Class<?> object_Class = inserts.getClass();
            String object_Name = object_Class.getName().split("\\.")[2];

            switch(object_Name) {
                case "String":
                    preparedStatement.setString(index, (String) inserts[insert]);
                    break;
                case "Integer":
                    preparedStatement.setInt(index, (int) inserts[insert]);
                    break;
                case "Double":
                    preparedStatement.setDouble(index, (double) inserts[insert]);
                    break;
                case "Boolean":
                    preparedStatement.setBoolean(index, (boolean) inserts[insert]);
                    break;
                default:
                    break;
            }
            insert++;
        }
    }
}
