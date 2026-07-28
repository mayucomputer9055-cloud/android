package mydb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import classes.TableItems;

public  class MyDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Users.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "User";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "EMAIL";
    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " ( " + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT NOT NULL, "
                + COL3 + " TEXT" + " ) ");
        Log.d("10001","Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL2,name);
        cv.put(COL3,email);

        long res = db.insert(TABLE_NAME,null,cv);
        Log.d("1001","record of " + name + " inserted....");
        return res != -1;
    }

    public boolean updateData(String id, String name, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2,name);
        cv.put(COL3,email);

        int res = db.update(TABLE_NAME,cv,COL1 + "=?",new String[]{id});
        return res > 0;

    }

    public boolean deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_NAME,COL1 + "=?", new String[]{id});
        return res > 0;
    }

    Cursor getData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public List<TableItems> getDataList()
    {
        List<TableItems> ti = new ArrayList<>();
        Cursor c = getData();

        if(c.moveToFirst())
        {
            do
            {

                String id,name,email;
                id = c.getString(c.getColumnIndexOrThrow(COL1));
                name = c.getString(c.getColumnIndexOrThrow(COL2));
                email = c.getString(c.getColumnIndexOrThrow(COL3));
                Log.d("1001",id + " " + name);
                ti.add(new TableItems(id,name,email));
            }while (c.moveToNext());
        }
        c.close();
        return ti;
    }
}
