package ca.bcit.cst.seta2016.invoker;

import android.os.Parcel;
import android.os.Parcelable;

public class EventCard implements Parcelable {
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

    protected EventCard(Parcel in) {
        child = in.readString();
        date = in.readString();
        event = in.readString();
        desc = in.readString();
        rank = in.readString();
    }

    public static final Creator<EventCard> CREATOR = new Creator<EventCard>() {
        @Override
        public EventCard createFromParcel(Parcel in) {
            return new EventCard(in);
        }

        @Override
        public EventCard[] newArray(int size) {
            return new EventCard[size];
        }
    };

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

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(child);
        parcel.writeString(event);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
