package pau.kelezber;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;


public class skordata extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "skordata2";
    private static final String TABLE_NAME = "Skor2";
    private static String KULLANICI_ID = "id";
    private static String DOGRU = "dogru";
    private static String YANLIS = "yanlis";
    public skordata(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db2) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KULLANICI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DOGRU + " TEXT,"
                + YANLIS + " TEXT" + ")";
        db2.execSQL(CREATE_TABLE);
    }

    public void skorEkle(String dogru,String yanlis) {
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DOGRU, dogru);
        values.put(YANLIS, yanlis);
        db2.insert(TABLE_NAME, null, values);
        db2.close();
    }

    public ArrayList<HashMap<String, String>> skorlar(){
        SQLiteDatabase db2 = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db2.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> skorliste = new ArrayList<HashMap<String, String>>();

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0; i<cursor.getColumnCount();i++)
                {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }

                skorliste.add(map);
            } while (cursor.moveToNext());
        }
        db2.close();
        return skorliste;
    }

    public void skorTemizle(){
        SQLiteDatabase db2 = this.getWritableDatabase();
        db2.delete(TABLE_NAME, null, null);
        db2.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

