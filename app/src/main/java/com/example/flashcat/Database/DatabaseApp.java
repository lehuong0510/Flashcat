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
import com.example.flashcat.Model.Flashcard;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;

public class DatabaseApp extends SQLiteOpenHelper {
    public DatabaseApp(@Nullable Context context) {
        super(context, Databasename, null, DATABASE_VERSION);
    }
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public  static final  String Databasename = "DatabaseFCa";
    public static final int DATABASE_VERSION = 1;
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
                idF + " Integer Primary key AUTOINCREMENT, "
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
        String triggerSQL = "CREATE TRIGGER IF NOT EXISTS update_flashcard_count AFTER INSERT ON " +
                TableNameF + " BEGIN " +
                "UPDATE " + TableName + " SET " + numberFlashcard + " = " + numberFlashcard + " + 1 " +
                "WHERE " + id + " = NEW." + idDesk + "; END;";
        db.execSQL(triggerSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TableName);
        db.execSQL("Drop table if exists " + TableNameF);
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
    public ArrayList<Desk> getAllDesk() {
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
    //update Desk
    public void updateDesk(int IdDesk, Desk desk){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(name, desk.getName_deck());
        values.put(status, desk.isStatus_deck()==true?1:0);
        String createDayText = desk.getCreate_day().format(DATE_TIME_FORMATTER);
        values.put(createDay, createDayText);
        values.put(idAccount, desk.getID_Account());
        values.put(numberFlashcard, desk.getNumber_flashcard());
        db.update(TableName, values, id + "=?", new String[]{String.valueOf(IdDesk)});
        db.close();
        Log.d("k", "updateDesk: "+id);

    }
    //xoa desk theo id
    public void deleteDesk(int idDesk){
        SQLiteDatabase db = getWritableDatabase();
        String sql ="Delete from " + TableName+ " Where idDesk = " + idDesk;
        db.execSQL(sql);
        db.close();
    }
    //xoa tat cac desk trong data
    public void deleteAllDesk() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TableName, null, null);
        db.close();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addFlashcard(Flashcard flashcard) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(term, flashcard.getTerm());
        values.put(definition, flashcard.getDefinition());
        values.put(example, flashcard.getExample());
        values.put(sound, flashcard.getSound());
        values.put(status, flashcard.isStatus()==true?1:0);
        String updateDayText = flashcard.getUpdate_day().format(DATE_TIME_FORMATTER);
        values.put(updateDay, updateDayText);
        values.put(idDesk, flashcard.getID_Deck());
        db.insert(TableNameF, null, values);
//        DeskDatabase deskDatabase = new DeskDatabase(mContext,"Database6", null, 1);
//        deskDatabase.updateNumberFlashcard(flashcard.getID_Deck());
        db.close();
    }
    public ArrayList<Flashcard> getAllFlashCard() {
        ArrayList<Flashcard> list = new ArrayList<>();
        // cau truy van
        String sql = "Select * from " + TableNameF;
        //Lay doi tuong csdl sqlLite
        SQLiteDatabase db = this.getReadableDatabase();
        // Chay cau truy van tra ve cursor
        Cursor cursor = db.rawQuery(sql, null);
        //Tao ArrayList<Contact> de tra ve
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String updatedayText = cursor.getString(6);
                LocalDateTime updateday = LocalDateTime.parse(updatedayText,DATE_TIME_FORMATTER);
                Flashcard flashcard = new Flashcard(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getInt(5)==1?true:false,updateday,cursor.getInt(7));
                list.add(flashcard);
            }
        }
        cursor.close();
        db.close();
        return list;
    }
    //select all flashcard theo desk
    public ArrayList<Flashcard> getAllContactDesk(int id_desk) {
        ArrayList<Flashcard> list = new ArrayList<>();
        // cau truy van
        String sql = "Select * from " + TableNameF + " Where idDesk = ? ";
        //Lay doi tuong csdl sqlLite
        SQLiteDatabase db = this.getReadableDatabase();
        // Chay cau truy van tra ve cursor
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id_desk)});
        //Tao ArrayList<Contact> de tra ve
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String createDayText = cursor.getString(6);
                LocalDateTime updateday = LocalDateTime.parse(createDayText,DATE_TIME_FORMATTER);
                Flashcard flashcard = new Flashcard(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getInt(5)==1?true:false,updateday,cursor.getInt(7));
                list.add(flashcard);
            }
        }
        cursor.close();
        db.close();
        return list;
    }
    //select flashcard theo iD
    public Flashcard getFlashcardID(int id_flashcard) {
        Flashcard flashcard = new Flashcard() ;
        // cau truy van
        String sql = "Select * from " + TableNameF + " Where idF = ? ";
        //Lay doi tuong csdl sqlLite
        SQLiteDatabase db = this.getReadableDatabase();
        // Chay cau truy van tra ve cursor
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id_flashcard)});
        //Tao ArrayList<Contact> de tra ve
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String createDayText = cursor.getString(6);
                LocalDateTime updateday = LocalDateTime.parse(createDayText,DATE_TIME_FORMATTER);
                 flashcard = new Flashcard(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getInt(5)==1?true:false,updateday,cursor.getInt(7));
            }
        }
        cursor.close();
        db.close();
        return flashcard;
    }
    //cap nhat flashcard
    public void updateFlashcard(int IdFlashcard, Flashcard flashcard){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(term, flashcard.getTerm());
        values.put(definition, flashcard.getDefinition());
        values.put(example, flashcard.getExample());
        values.put(sound, flashcard.getSound());
        values.put(status, flashcard.isStatus()==true?1:0);
        String updateDayText = flashcard.getUpdate_day().format(DATE_TIME_FORMATTER);
        values.put(updateDay, updateDayText);
        values.put(idDesk, flashcard.getID_Deck());
        db.update(TableNameF, values, idF + "=?", new String[]{String.valueOf(IdFlashcard)});
        db.close();
        Log.d("k", "updateDesk: "+IdFlashcard);

    }
    //xoa flashcard theo id
    public void deleteFlashcard(int idFlashcard){
        SQLiteDatabase db = getWritableDatabase();
        String sql ="Delete from " + TableNameF+ " Where idFlashcard = " + idFlashcard;
        db.execSQL(sql);
        db.close();
    }
    //xoa tat cac flashcard trong desk
    public void deleteFlashcardDesk(int IdDesk){
        SQLiteDatabase db = getWritableDatabase();
        String sql ="Delete from " + TableNameF+ " Where idDesk = " + IdDesk;
        db.execSQL(sql);
        db.close();
    }
}