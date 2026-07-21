package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public abstract class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Users.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "User";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "EMAIL";
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
}
