import java.io.Serializable;
import java.util.ArrayList;

public class Log implements Serializable{
    private Date date;
    private String description;

    public Log (Date date, String description){
        this.date = date;
        this.description = description;
    }

    public Date getDate(){
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Log{" +
                "date=" + date +
                '}';
    }
}
