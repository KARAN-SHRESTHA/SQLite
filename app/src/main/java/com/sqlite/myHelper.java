package com.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class myHelper extends SQLiteOpenHelper {

    public static final String databaseName = "DictionaryDB";
    public static final int dbVersion = 1;

    public myHelper(Context context)
    {
        super(context, databaseName, null,dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE WORDS (WordID INTEGER PRIMARY KEY AUTOINCREMENT, Word TEXT, Meaning TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(String word, String meaning, SQLiteDatabase db)
    {
        try {
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("Word", word);
            contentvalues.put("Meaning", meaning);
            db.insert("Words", null, contentvalues);
            return true;
        }
        catch (Exception e)
        {
            Log.d("Error", e.toString());
            return false;
        }
    }

    public List<Word> getAllWords(SQLiteDatabase db)
    {
        List<Word> dictionaryList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from WORDS", null);

        if (cursor.getCount() > 0)
        {
            while (cursor.moveToNext())
            {
                dictionaryList.add(new Word(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            }
        }
        return dictionaryList;
    }

    public List<Word> getWordByName(String word, SQLiteDatabase db)
    {

        List<Word> dictionaryList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from WORDS where Word ='"+word+"'", null );

        if(cursor.getCount() > 0)
        {
            while (cursor.moveToNext())
            {
                dictionaryList.add(new Word(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            }
        }
        return dictionaryList;
    }
}
