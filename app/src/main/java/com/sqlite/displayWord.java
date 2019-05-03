package com.sqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class displayWord extends AppCompatActivity {

    private ListView listWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_word);

        listWords = findViewById(R.id.listWords);

        loadWord();
    }

    private void loadWord()
    {
        final myHelper Myhelper = new myHelper(this);
        final SQLiteDatabase sqldb = Myhelper.getWritableDatabase();

        List<Word> wordList = new ArrayList<>();
        wordList = Myhelper.getAllWords(sqldb);

        HashMap<String, String> hashMap = new HashMap<>();

        for (int i=0;i< wordList.size(); i++)
        {
            hashMap.put(wordList.get(i).getWord(), wordList.get(i).getMeaning());

        }

        ArrayAdapter<String> StringArrayAdapter = new ArrayAdapter<>(

                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(hashMap.keySet())
        );

        listWords.setAdapter(StringArrayAdapter);
    }

}
