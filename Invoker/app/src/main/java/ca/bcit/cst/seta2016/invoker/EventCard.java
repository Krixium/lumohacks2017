package ca.bcit.cst.seta2016.invoker;

/**
 * EventCard.java
 *
 * Data class that stores a user described event concerning a child with a date.
 */
public class EventCard {
    private int id;
    private String child;
    private String date;
    private String event;
    private String desc;
    private String rank;

    public EventCard(int id, String child, String date, String event, String desc, String rank) {
        this.id = id;
        this.child = child;
        this.date = date;
        this.event = event;
        this.desc = desc;
        this.rank = rank;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
