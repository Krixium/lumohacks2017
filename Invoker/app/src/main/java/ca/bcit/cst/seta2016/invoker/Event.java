package ca.bcit.cst.seta2016.invoker;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Miranda on 9/17/2017.
 */

public class Event implements Parcelable {
    private int id;
    private String child;
    private String date;
    private String event;
    private String desc;
    private String rank;

    public Event() {
    }

    public Event(int id, String child, String date, String event, String desc, String rank) {
        this.id = id;
        this.child = child;
        this.date = date;
        this.event = event;
        this.desc = desc;
        this.rank = rank;
    }

    protected Event(Parcel in) {
        child = in.readString();
        date = in.readString();
        event = in.readString();
        desc = in.readString();
        rank = in.readString();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
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
