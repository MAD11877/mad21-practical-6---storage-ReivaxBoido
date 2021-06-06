package sg.edu.np.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.sax.ElementListener;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper{
    public DBHandler(@Nullable Context context) {
        super(context, "USERSDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS (name TEXT, description TEXT, id INTEGER, follow INTEGER) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS USERS");
            onCreate(db);
    }

    public void addUser(User user, int id)
    {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("description", user.getDescription());
        values.put("id", id);

        if (user.isFollow()) values.put("follow", 0);
        else values.put("follow", 1);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("USERS", null, values);
        db.close();
    }

    public ArrayList<User> getUser()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users", null); //where name =\"" + name + "\""
        User u = null;
        ArrayList<User> list = new ArrayList<>();

        while (cursor.moveToNext())
        {
            u = new User();
            u.setName(cursor.getString(0));
            u.setDescription(cursor.getString(1));

            list.add(u);
        }

        cursor.close();
        db.close();

        return list;
    }

    public void updateUser(User user)
    {
        String name = user.getName();
        String description = user.getDescription();
        int follow;
        if (user.isFollow()) follow = 1;
        else follow = 0;

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("UPDATE USERS SET name = \"" + name + "\", " +
                "description = \"" + description + "\", follow = \"" + follow + "\" WHERE name =\"" + name +"\"", null);
        db.close();
    }

}
