import java.util.Calendar;
import java.util.Date;

public class Task {
    private String title;
    private String description;

    private Date date;

    public Task(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getMillis(){
        return date.getTime();
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
