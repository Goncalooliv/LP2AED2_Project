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

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Id: " + id + " || Nome: " + name + " || Type: " + type;
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


    public ST<Date, ArrayList<Poi>> showVisitedPoiMultiple(String subrede, Date dataInicial, Date dataFinal) {
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

    /**
     * Percorre a visitedPoi e caso a data esteja entre o periodo definido retorna todos os POIs visitados no periodo de tempo
     * Requisito 5.a
     * @param subrede dos POIs a pesquisar
     * @param dataInicial do periodo de tempo
     * @param dataFinal do periodo de tempo
     * @return POIs que verificam a condição
     */
    public ST<Integer, Poi> showVisitedPoi(String subrede, Date dataInicial, Date dataFinal){
        ST<Integer, Poi> visited = new ST<>();
        for(Date date : visitedPoi.keys()){
            if(dataInicial.beforeDate(date) && dataFinal.afterDate(date)){
                if (subrede.equalsIgnoreCase("global") || visitedPoi.get(date).getLocation().getSubrede().equalsIgnoreCase(subrede)) {
                    visited.put(visitedPoi.get(date).getIdPoi(), visitedPoi.get(date));
                }
            }
        }
        return visited;
    }

    /**
     * Vai ver todos os POIs que existem e se alguns dos IDs dos POIs nao estiver presente na showVisitedPoi colocamos esse Poi numa ST
     * @param subrede dos POIs a pesquisar
     * @param dataInicial do periodo de tempo
     * @param dataFinal do periodo de tempo
     * @return ST dos Pois que nao foram visitados no intervalo de tempo definido
     */
    public ST<Integer, Poi> showNotVisitedPoi(String subrede, Date dataInicial, Date dataFinal){
        ST<Integer, Poi> notVisited = new ST<>();
        for(int poiID : DataBase.poiST.keys()){
            if(!showVisitedPoi(subrede, dataInicial, dataFinal).contains(poiID) && (subrede.equalsIgnoreCase("global") || DataBase.poiST.get(poiID).getLocation().getSubrede().equalsIgnoreCase(subrede))){
                notVisited.put(poiID, DataBase.poiST.get(poiID));
            }
        }
        return notVisited;
    }

    public void printIntervaloTempo(Date dataInicial, Date dataFinal, String subrede) {
        System.out.println("No periodo de tempo: " + dataInicial.toString() + " - " + dataFinal.toString() + " || Subrede: " + subrede);
    }

}
