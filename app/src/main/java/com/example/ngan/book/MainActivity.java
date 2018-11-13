package com.example.ngan.book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvBook;
    private Button btnAdd,btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvBook = findViewById(R.id.lvBook);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(t);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(MainActivity.this, UpdateActivity.class);
                startActivity(t);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        BookDBHandler db = new BookDBHandler(this);
        if(db != null && db.getBooksCursor() != null && db.getBooksCursor().getCount() > 0){
                BookAdapter adapter = new BookAdapter(this,db.getBooksCursor());
                lvBook.setAdapter(adapter);
        }
    }
}
