package com.example.ngan.book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateActivity extends AppCompatActivity {

    private EditText txtBookId, txtTitle, txtPrice;
    private Button btnAddBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        txtBookId = findViewById(R.id.txtId);
        txtTitle = findViewById(R.id.txtTitle);
        txtPrice = findViewById(R.id.txtPrice);
        btnAddBook = findViewById(R.id.btnAddBook);
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDBHandler db = new BookDBHandler(CreateActivity.this);
                BookEntity b = new BookEntity();
                b.setBookId(Integer.parseInt(txtBookId.getText().toString()));
                b.setTitle(txtTitle.getText().toString());
                b.setPrice(Integer.parseInt(txtPrice.getText().toString()));
                db.add(b);

                Intent t = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(t);
            }
        });
    }
}
