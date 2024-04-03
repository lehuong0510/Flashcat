package com.example.flashcat.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.flashcat.Model.Flashcard;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;

public class FlashDatabase extends SQLiteOpenHelper {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final String TableName = "FlashcardTable";
    public static final String idF = "idFlashcard";
    public static final String term = "term";
    public static final String definition = "definition";
    public static final String example = "example";
    public static final String sound = "sound";
    public static final String status = "status";
    public static final String updateDay = "updateDay";
    public static final String idDesk = "idDesk";
    private Context mContext;



    public FlashDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "Create table if not exists " + TableName + "(" +
                idF + " Integer Primary key, "
                + term + " Text, "
                + definition+ " Text, "
                + example+ " Text, "
                + sound+ " Text, "
                + status + " Integer, "
                + updateDay + " Text, "
                + idDesk + " Integer)";
        try {
            db.execSQL(sqlCreate);
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
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addFlashcard(Flashcard flashcard) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(idF, flashcard.getID_Flashcard());
        values.put(term, flashcard.getTerm());
        values.put(definition, flashcard.getDefinition());
        values.put(example, flashcard.getExample());
        values.put(sound, flashcard.getSound());
        values.put(status, flashcard.isStatus()==true?1:0);
        String updateDayText = flashcard.getUpdate_day().format(DATE_TIME_FORMATTER);
        values.put(updateDay, updateDayText);
        values.put(idDesk, flashcard.getID_Deck());
        db.insert(TableName, null, values);
//        DeskDatabase deskDatabase = new DeskDatabase(mContext,"Database6", null, 1);
//        deskDatabase.updateNumberFlashcard(flashcard.getID_Deck());
        db.close();
    }
    public ArrayList<Flashcard> getAllContact() {
        ArrayList<Flashcard> list = new ArrayList<>();
        // cau truy van
        String sql = "Select * from " + TableName;
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
    public ArrayList<Flashcard> getAllContactDesk(int id_desk) {
        ArrayList<Flashcard> list = new ArrayList<>();
        // cau truy van
        String sql = "Select * from " + TableName + " Where idDesk = ? ";
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
}
