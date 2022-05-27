import edu.princeton.cs.algs4.RedBlackBST;


import java.io.Serializable;
import java.util.ArrayList;

public class Node implements Serializable {
    private int id;
    private String name;
    private Location location;
    private PoiType type;

    RedBlackBSTProj<String, String> tagST = new RedBlackBSTProj<>();
    ArrayList<String> tagArray = new ArrayList<>();

    public Node(int id,String name, Location location, PoiType type) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;
    }

    public PoiType getType() {
        return type;
    }

    public void setType(PoiType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Id: " + id + " || Nome: " + name + " || Location " + location;
    }
}