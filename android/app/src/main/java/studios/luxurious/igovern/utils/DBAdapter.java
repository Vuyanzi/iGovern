package studios.luxurious.igovern.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBAdapter {
    private static final String DB_NAME = "iGovernDB";
    private static final int DB_VER = 1;
    private static final String TABEL_POSTS = "table_posts";
    public static final String ID_POSTS = "id";
    public static final String TITLE_POSTS = "title";
    public static final String LOCATION_POST = "location";
    public static final String MESSAGE_POST = "message";
    public static final String STATUS_POST = "status";
    public static final String TYPE_POST = "type";

    private static final String DB_CREATE_TABLE = "CREATE TABLE "
            + TABEL_POSTS + "(" + ID_POSTS + " integer primary key, "
            + TITLE_POSTS + " text not null, " + LOCATION_POST + " text not null, " + MESSAGE_POST + " text not null, " + STATUS_POST + " integer not null, " + TYPE_POST
            + " integer not null ); ";

    private String[] column_post = {ID_POSTS, TITLE_POSTS, LOCATION_POST, MESSAGE_POST,STATUS_POST,TYPE_POST};

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    private final Context context;

    public DBAdapter(Context context) {
        this.context = context;
    }

    public DBAdapter open() {
        dbHelper = new DatabaseHelper(context, DB_NAME, DB_VER);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertNewPost(String title, String location, String message, int status, int type) {

        ContentValues cv = new ContentValues();
        cv.put(TITLE_POSTS, title);
        cv.put(LOCATION_POST, location);
        cv.put(MESSAGE_POST, message);
        cv.put(STATUS_POST, status);
        cv.put(TYPE_POST, type);

        return db.insert(TABEL_POSTS, null, cv) > 0;
    }


    public boolean deletePost(int id) {
        return db.delete(TABEL_POSTS, ID_POSTS + "=" + id, null) > 0;
    }

    public Cursor getAllPostData() {
        return db.query(TABEL_POSTS, column_post, null, null, null, null,
                ID_POSTS + " DESC");
    }



    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, int version) {
            super(context, DB_NAME, null, DB_VER);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DB_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABEL_POSTS);
            onCreate(db);
        }

    }


}
