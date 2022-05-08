import edu.princeton.cs.algs4.ST;

import java.io.Serializable;

public class Location implements Serializable{
    private String subrede;
    private double latitude;
    private double longitude;


    public Location(String subrede, double latitude, double longitude){
        this.subrede = subrede;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getSubrede() {
        return subrede;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Latitude: " + latitude + " || Longitude: " + longitude + " || Subrede: " + subrede + "\n";
    }

}
