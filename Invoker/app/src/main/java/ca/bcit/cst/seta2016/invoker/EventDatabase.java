package ca.bcit.cst.seta2016.invoker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * EventDatabase.java
 *
 * Binds the SQLite database to a wrapping java class.
 */
public final class EventDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "eventInfo";
    private static final String TABLE_NAME = "entry";
    private static final String KEY_ID = "ID";
    private static final String KEY_CHILD = "child";
    private static final String KEY_DATE = "date";
    private static final String KEY_EVENT = "event";
    private static final String KEY_DESC = "description";
    private static final String KEY_RANK = "rank";

    public EventDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CHILD + " TEXT,"
                + KEY_DATE + " DATE," + KEY_EVENT + " TEXT," + KEY_DESC
                + " TEXT," + KEY_RANK + " INTEGER" + ")";
        db.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public void addEvent(EventCard eventCard) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CHILD, eventCard.getChild());
        values.put(KEY_DATE, eventCard.getDate());
        values.put(KEY_EVENT, eventCard.getEvent());
        values.put(KEY_DESC, eventCard.getDesc());
        values.put(KEY_RANK, eventCard.getRank());

        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    public EventCard getEventCard(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
                        KEY_CHILD, KEY_DATE, KEY_EVENT, KEY_DESC, KEY_RANK }, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        EventCard eventCard = new EventCard(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5));

        cursor.close();
        return eventCard;
    }

    public List<EventCard> getAllEvents() {
        List<EventCard> eventCardList = new ArrayList<EventCard>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase(); // Might only work with getWritableDatabase
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                EventCard eventCard = new EventCard(
                        Integer.parseInt(cursor.getString(0)),  // ID
                        cursor.getString(1),                    // Child
                        cursor.getString(2),                    // Date
                        cursor.getString(3),                    // Event
                        cursor.getString(4),                    // Desc
                        cursor.getString(5)                     // Rank
                );
                eventCardList.add(eventCard);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return eventCardList;
    }

    public int getEventsCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int updateEventCard(EventCard eventCard) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_CHILD, eventCard.getChild());
        values.put(KEY_DATE, eventCard.getDate());
        values.put(KEY_EVENT, eventCard.getEvent());
        values.put(KEY_DESC, eventCard.getDesc());
        values.put(KEY_RANK, eventCard.getRank());

        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(eventCard.getID())});
    }


    public void deleteEventCard(EventCard eventCard) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(eventCard.getID()) });
        db.close();
    }
}
