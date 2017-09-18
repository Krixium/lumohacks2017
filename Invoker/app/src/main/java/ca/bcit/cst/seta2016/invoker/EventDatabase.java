package ca.bcit.cst.seta2016.invoker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miranda on 9/17/2017.
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

    public void addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CHILD, event.getChild());
        values.put(KEY_DATE, event.getDate());
        values.put(KEY_EVENT, event.getEvent());
        values.put(KEY_DESC, event.getDesc());
        values.put(KEY_RANK, event.getRank());

        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    public Event getEvent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
                        KEY_CHILD, KEY_DATE, KEY_EVENT, KEY_DESC, KEY_RANK }, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Event event = new Event(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

        return event;
    }

    public List<Event> getAllEvents() {
        List<Event> eventList = new ArrayList<Event>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Event shop = new Event();
                shop.setID(Integer.parseInt(cursor.getString(0)));
                shop.setChild(cursor.getString(1));
                shop.setDate(cursor.getString(2));
                shop.setEvent(cursor.getString(3));
                shop.setDesc(cursor.getString(4));
                shop.setRank(cursor.getString(5));

                eventList.add(shop);
            } while (cursor.moveToNext());
        }

        return eventList;
    }

    public int getEventsCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int updateShop(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CHILD, event.getChild());
        values.put(KEY_DATE, event.getDate());
        values.put(KEY_EVENT, event.getEvent());
        values.put(KEY_DESC, event.getDesc());
        values.put(KEY_RANK, event.getRank());

        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(event.getID())});
    }


    public void deleteShop(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(event.getID()) });
        db.close();
    }


}
