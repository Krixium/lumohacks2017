package ca.bcit.cst.seta2016.invoker;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * CardData.java
 *
 * Description: Data model for the cards on the homepage of the application.
 */
class CardData implements Parcelable{
    private String textTitle;
    private String textDesc;

    public CardData(String textTitle, String textDesc) {
        this.textTitle = textTitle;
        this.textDesc = textDesc;
    }

    protected CardData(Parcel in) {
        textTitle = in.readString();
        textDesc = in.readString();
    }

    public static final Creator<CardData> CREATOR = new Creator<CardData>() {
        @Override
        public CardData createFromParcel(Parcel in) {
            return new CardData(in);
        }

        @Override
        public CardData[] newArray(int size) {
            return new CardData[size];
        }
    };

    public String getTextTitle() {
        return textTitle;
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }

    public String getTextDesc() {
        return textDesc;
    }

    public void setTextDesc(String textDesc) {
        this.textDesc = textDesc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(textTitle);
        parcel.writeString(textDesc);
    }

    @Override
    public String toString() {
        return "{" + textTitle + " : " + textDesc + "}";
    }
}
