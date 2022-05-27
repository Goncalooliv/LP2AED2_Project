import edu.princeton.cs.algs4.RedBlackBST;

import java.io.Serializable;
import java.util.ArrayList;

public class Way extends DirectedEdgeProj implements Serializable {
    private int id;
    private int idNodeInicial;
    private int idNodeFinal;
    private double distance;
    private double averageTime;

    RedBlackBSTProj<String,String> tagST = new RedBlackBSTProj<>();
    ArrayList<String> tagArray = new ArrayList<>();

    public Way(int id, int idNodeInicial, int idNodeFinal, double distance, double averageTime) {
        super(idNodeInicial,idNodeFinal,distance,averageTime);
        this.id = id;
        this.idNodeInicial = idNodeInicial;
        this.idNodeFinal = idNodeFinal;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNodeInicial() {
        return idNodeInicial;
    }

    public void setIdNodeInicial(int idNodeInicial) {
        this.idNodeInicial = idNodeInicial;
    }

    public int getIdNodeFinal() {
        return idNodeFinal;
    }

    public void setIdNodeFinal(int idNodeFinal) {
        this.idNodeFinal = idNodeFinal;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
