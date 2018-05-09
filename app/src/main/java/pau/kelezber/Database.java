package pau.kelezber;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;

public class Database extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION = 13;
    private static final String DATABASE_NAME = "db_kelime2";
    private static final String TABLE_NAME = "sorular12";
    private static String SORU = "soru";
    private static String SORU_ID = "id";
    private static String CEVAP = "cevap";
    private static String YILDIZ = "yildiz";

    public Database (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +"("
                + SORU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SORU + " TEXT,"
                + CEVAP + " TEXT,"
                + YILDIZ + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);

    }

    public void kelimeSil(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, SORU_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    public void kelimeEkle(String soru, String cevap, String yildiz) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(SORU, soru);
        v.put(CEVAP, cevap);
        v.put(YILDIZ,yildiz);
        db.insert(TABLE_NAME, null, v);
        db.close();
    }

    public HashMap<String, String> kelimeleriGoster(int id){

        HashMap<String,String> kelimeler = new HashMap<String,String>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME+ " WHERE id="+id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        c.moveToFirst();
        if(c.getCount() > 0){

            kelimeler.put(SORU, c.getString(1));
            kelimeler.put(CEVAP, c.getString(2));
            kelimeler.put(YILDIZ, c.getString(3));
                    }
        c.close();
        db.close();
        return kelimeler;

    }

    public ArrayList<HashMap<String, String>> kelimeleriListele(){

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME ;
        Cursor c = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> kelimeListesi = new ArrayList<HashMap<String, String>>();

        if (c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0; i<c.getColumnCount();i++)
                {
                    map.put(c.getColumnName(i), c.getString(i));
                }

                kelimeListesi.add(map);
            } while (c.moveToNext());
        }
        db.close();

        return kelimeListesi;
    }


    public void kelimeDuzenle(String soru, String cevap, String yildiz,int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues v = new ContentValues();
        v.put(SORU, soru);
        v.put(CEVAP, cevap);
        v.put(YILDIZ, yildiz);
        db.update(TABLE_NAME, v, SORU_ID + " = ?",new String[] { String.valueOf(id) });

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}