import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.ST;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class Poi {
    private int idPoi;
    private String poiName;
    private Location location;
    private PoiType poiType;
    private String details;

    public RedBlackBST<Date, Log> poiLog = new RedBlackBST<>();

    public Poi(int idPOI, String poiName, Location location, PoiType poiType, String details) {
        this.idPoi = idPOI;
        this.poiName = poiName;
        this.location = location;
        this.details = details;
        this.poiType = poiType;
        if (DataBase.subredeST.contains(location.getSubrede())) {
            DataBase.subredeST.put(location.getSubrede(), DataBase.subredeST.get(location.getSubrede()) + 1);
        } else {
            DataBase.subredeST.put(location.getSubrede(), 1);
        }
    }

    public PoiType getPoiType() {
        return poiType;
    }

    public void setPoiType(PoiType poiType) {
        this.poiType = poiType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getIdPoi() {
        return idPoi;
    }

    public void setIdPoi(int idPoi) {
        this.idPoi = idPoi;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public RedBlackBST<Date, Log> getPoiLog() {
        return poiLog;
    }

    /**
     * Todos os Users que visitaram um Certo POI dentro de um Periodo de Tempo
     * @return ST com os users que visitaram o POI
     */
    public ST<Integer, User> allUsersThatVisitedPeriod(Date dataInicial, Date dataFinal) {
        ST<Integer, User> users = new ST<>();
        for (int userID : DataBase.userST.keys()) {
            for (Date date : DataBase.userST.get(userID).visitedPoi.keys()) {
                if(dataInicial.beforeDate(date) && dataFinal.afterDate(date)){
                    if (DataBase.userST.get(userID).visitedPoi.get(date).getIdPoi() == this.idPoi) {
                        users.put(userID, DataBase.userST.get(userID));
                    }
                }
            }
        }
        return users;
    }

    //para usar para o now
    public ST<Integer, User> allUsersThatVisited(){
        ST<Integer,User> users = new ST<>();
        for(int userID : DataBase.userST.keys()){
            for(Date date : DataBase.userST.get(userID).visitedPoi.keys()){
                if(DataBase.userST.get(userID).visitedPoi.get(date).getIdPoi() == this.idPoi){
                    users.put(userID, DataBase.userST.get(userID));
                }
            }
        }
        return users;
    }


    public static void poiNotVisited(Date dataInicial, Date dataFinal){
        for(int poiID : DataBase.poiST.keys()){
            ST<Integer,User> user = DataBase.poiST.get(poiID).allUsersThatVisitedPeriod(dataInicial,dataFinal);
            if(user.size() == 0){
                System.out.println("Id: " + poiID + " || Name: " + DataBase.poiST.get(poiID).getPoiName());
            }
        }
    }
}
