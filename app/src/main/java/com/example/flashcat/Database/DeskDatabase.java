package com.example.flashcat.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.flashcat.Model.Desk;


import java.util.ArrayList;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;


@RequiresApi(api = Build.VERSION_CODES.O)
public class DeskDatabase extends SQLiteOpenHelper {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public DeskDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public static final String TableName = "DeskTable";
    public static final String id = "idDesk";
    public static final String name = "nameDesk";
    public static final String status = "Status";
    public static final String createDay = "createDay";
    public static final String idAccount = "idAccount";
    public static final String numberFlashcard = "numberFlashcard";
    public static final String TableNameF = "FlashcardTable";
    public static final String idF = "idFlashcard";
    public static final String term = "term";
    public static final String definition = "definition";
    public static final String example = "example";
    public static final String sound = "sound";
    public static final String statusF = "status";
    public static final String updateDay = "updateDay";
    public static final String idDesk = "idDesk";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "Create table if not exists " + TableName + "(" +
                id + " Integer Primary key AUTOINCREMENT, "
                + name + " Text, "
                + status + " Integer, "
                + createDay + " Text, "
                + idAccount + " Text, "
                + numberFlashcard + " Integer)";
            db.execSQL(sqlCreate);
        String sqlCreateFlashCard = "Create table if not exists " + TableNameF + "(" +
                idF + " Integer Primary key, "
                + term + " Text, "
                + definition+ " Text, "
                + example+ " Text, "
                + sound+ " Text, "
                + statusF + " Integer, "
                + updateDay + " Text, "
                + idDesk + " Integer)";
        try {
            db.execSQL(sqlCreateFlashCard);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TableName);
        //tao lai
        onCreate(db);
    }
    public void addDesk(Desk desk) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(name, desk.getName_deck());
        values.put(status, desk.isStatus_deck()==true?1:0);
        String createDayText = desk.getCreate_day().format(DATE_TIME_FORMATTER);
        values.put(createDay, createDayText);
        values.put(idAccount, desk.getID_Account());
        values.put(numberFlashcard, desk.getNumber_flashcard());
        db.insert(TableName, null, values);
        db.close();

    }
    public ArrayList<Desk> getAllContact() {
        ArrayList<Desk> list = new ArrayList<>();
        // cau truy van
        String sql = "Select * from " + TableName;
        //Lay doi tuong csdl sqlLite
        SQLiteDatabase db = this.getReadableDatabase();
        // Chay cau truy van tra ve cursor
        Cursor cursor = db.rawQuery(sql, null);
        //Tao ArrayList<Contact> de tra ve
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String createDayText = cursor.getString(3);
                LocalDateTime createDay = LocalDateTime.parse(createDayText,DATE_TIME_FORMATTER);
                Desk desk = new Desk(cursor.getInt(0),
                        cursor.getString(1), cursor.getInt(2)==1?true:false,
                        createDay, cursor.getString(4), cursor.getInt(5));
                Log.d("number", "getAllContact: " + String.valueOf(cursor.getInt(5)));
                list.add(desk);
            }
        }
        cursor.close();
        db.close();
        return list;
    }
    public void updateNumberFlashcard(int deskId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT COUNT(*) FROM " + FlashDatabase.TableName + " WHERE " + FlashDatabase.idDesk + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(deskId)});
        if (cursor != null && cursor.moveToFirst()) {
            int count = cursor.getInt(0);
            cursor.close();
            ContentValues values = new ContentValues();
            values.put(numberFlashcard, count);
            db.update(TableName, values, id + " = ?", new String[]{String.valueOf(deskId)});
        }
        db.close();
    }

}
