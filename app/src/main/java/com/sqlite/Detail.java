package com.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Detail extends AppCompatActivity {

    private TextView detailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailView = findViewById(R.id.detailView);

        Bundle b = getIntent().getExtras();

        if(b!=null)
        {
            String meaning = b.getString("Meaning");
            detailView.setText(meaning);
        }
        else
        {
            Toast.makeText(this, "No Meaning", Toast.LENGTH_SHORT).show();
        }

    }
}
