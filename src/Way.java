import edu.princeton.cs.algs4.RedBlackBST;

import java.util.ArrayList;

public class Way {
    private int id;
    private int idNodeInicial;
    private int idNodeFinal;
    private double weight;

    RedBlackBST<String,String> tagST = new RedBlackBST<>();
    ArrayList<String> tagArray = new ArrayList<>();

    public Way(int id, int idNodeInicial, int idNodeFinal, double weight) {
        this.id = id;
        this.idNodeInicial = idNodeInicial;
        this.idNodeFinal = idNodeFinal;
        this.weight = weight;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
