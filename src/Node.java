import edu.princeton.cs.algs4.RedBlackBST;


import java.util.ArrayList;

public class Node {
    private int id;
    private double latitude;
    private double longitude;

    RedBlackBST<String,String> tagST = new RedBlackBST<>();
    ArrayList<String> tagArray = new ArrayList<>();

    public Node(int id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Node " + id + " || Latitude: " + latitude + " || Longitude: " + longitude;
    }
}
