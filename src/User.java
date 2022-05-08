import edu.princeton.cs.algs4.*;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private int id;
    private String name;
    private Type type;

    public RedBlackBST<Date, Poi> visitedPoi = new RedBlackBST<>();

    public User(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public User(int id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    /**
     * Adiciona um POI à Symbol Table de POI's visitados pelo utilizador
     *
     * @param poiID do poi a adicionar aos visitados
     */
    public void addVisitedPOI(int poiID, Date dataVisita) {
        if (!DataBase.poiST.contains(poiID)) {
            System.out.println("There's no POI with ID " + poiID + " on the Symbol Table");
        } else {
            for (Date data : visitedPoi.keys()) {
                if (visitedPoi.get(data).getIdPoi() == poiID && dataVisita == visitedPoi.keys()) {
                    System.out.println("\nThis user already visited the POI: " + visitedPoi.get(data).getPoiName());
                    return;
                }
            }
            visitedPoi.put(dataVisita, DataBase.poiST.get(poiID));
        }
    }

    public ST<Date, ArrayList<Poi>> showVisitedPoi(String subrede, Date dataInicial, Date dataFinal) {
        ST<Date, ArrayList<Poi>> visited = new ST<>();
        for (Date date : visitedPoi.keys()) {
            if (dataInicial.beforeDate(date) && dataFinal.afterDate(date)) {
                if (subrede.equalsIgnoreCase("global") || visitedPoi.get(date).getLocation().getSubrede().equalsIgnoreCase(subrede)) {
                    visited.put(date, new ArrayList<>());
                    visited.get(date).add(visitedPoi.get(date));
                }
            }

        }
        return visited;
    }

    public ST<Integer, ArrayList<Poi>> showNonVisitedPoi(String subrede, Date dataInicial, Date dataFinal) {
        ST<Integer, ArrayList<Poi>> nonVisitedPoi = new ST<>();
        for (int idPoi : DataBase.poiST.keys()) {
            for(Date date : visitedPoi.keys()){
                if(showVisitedPoi(subrede, dataInicial, dataFinal).contains(date)){
                    if(!(idPoi == visitedPoi.get(date).getIdPoi())){
                        nonVisitedPoi.put(idPoi,new ArrayList<>());
                        nonVisitedPoi.get(idPoi).add(DataBase.poiST.get(idPoi));
                    }
                }
            }
        }
        return nonVisitedPoi;
    }

    public void printIntervaloTempo(Date dataInicial, Date dataFinal) {
        System.out.println("No periodo de tempo: " + dataInicial.toString() + " - " + dataFinal.toString());
    }

}
