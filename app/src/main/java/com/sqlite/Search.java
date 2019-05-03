package com.sqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Search extends AppCompatActivity {

    private EditText etSearch;
    private Button btnSearch;
    private ListView Listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        Listview = findViewById(R.id.Listview);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a = etSearch.getText().toString();
                //Toast.makeText(Search.this, a, Toast.LENGTH_SHORT).show();
                loadWord(a);
            }
        });

    }


    private void loadWord(String word)
    {
        final myHelper Myhelper = new myHelper(this);
        final SQLiteDatabase sqldb = Myhelper.getWritableDatabase();

        List<Word> wordList = new ArrayList<>();
        wordList = Myhelper.getWordByName(word, sqldb);

        final HashMap<String, String> hashMap = new HashMap<>();

        for (int i=0;i< wordList.size(); i++)
        {
            hashMap.put(wordList.get(i).getWord(), wordList.get(i).getMeaning());

        }

        ArrayAdapter<String> StringArrayAdapter = new ArrayAdapter<>(

                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(hashMap.keySet())
        );

        Listview.setAdapter(StringArrayAdapter);

        Listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String key = parent.getItemAtPosition(position).toString();
                String meaning = hashMap.get(key);

                Intent intent = new Intent(Search.this, Detail.class);
                intent.putExtra("Meaning", meaning);
                startActivity(intent);

            }
        });

    }
}
