import java.io.Serializable;
import java.util.ArrayList;

public class Log implements Serializable{
    private Date date;
    private String description;
    private String userLog;
    private Integer poiID;

    public Log (Date date, String description, String userLog, Integer poiID){
        this.date = date;
        this.description = description;
        this.userLog = userLog;
        this.poiID = poiID;
    }

    public Integer getPoiID() {
        return poiID;
    }

    public Date getDate(){
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Data: " + date + ", Description: " + description + ", User: " + userLog + ", PoiID: " + poiID;
    }
}
