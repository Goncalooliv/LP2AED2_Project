import java.io.Serializable;
import java.util.ArrayList;

public class Log implements Serializable{
    private Date date;
    private String description;
    private String userLog;

    public Log (Date date, String description, String userLog){
        this.date = date;
        this.description = description;
        this.userLog = userLog;
    }

    public Date getDate(){
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Data: " + date + ", Description: " + description + ", User: " + userLog;
    }
}
