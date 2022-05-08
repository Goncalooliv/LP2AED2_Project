import edu.princeton.cs.algs4.RedBlackBST;

import java.util.ArrayList;

public class Poi {
    private int idPoi;
    //public static ArrayList<Integer> userID;
    private String poiName;
    private Location location;
    private PoiType poiType;
    private String details;

    public Poi(int idPOI, String poiName, Location location, PoiType poiType, String details) {
        this.idPoi =  idPOI;
        this.poiName = poiName;
        this.location = location;
        this.details = details;
        this.poiType = poiType;
        if(DataBase.subredeST.contains(location.getSubrede())){
            DataBase.subredeST.put(location.getSubrede(), DataBase.subredeST.get(location.getSubrede()) + 1);
        }else{
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

    public int getIdPoi(){return idPoi;}

    public void setIdPoi(int idPoi){
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
        return DataBase.poiLog;
    }

    /*public ST<Integer, User> UsersThatVisited(Date initialDate, Date finalDate){
        ST<Integer, User> visitantes = new ST<>();
        for(int userID : User.userST.keys()){
            for(Date data : User.userST.get(userID).visitedPoi.keys()){
                if(data.afterDate(initialDate) && data.beforeDate(finalDate)){
                    User.userST.get(userID);
                    if(User.userST.get(userID).visitedPoi.get(data).getIdPoi() == this.idPoi){
                        visitantes.put(userID, User.userST.get(userID));
                    }
                }
            }
        }
        return visitantes;
    }*/
}
