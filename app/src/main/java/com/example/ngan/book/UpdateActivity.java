package com.example.ngan.book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {
    private EditText txtBookId, txtTitle, txtPrice;
    private Button btnUpdateBook,btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        txtBookId = findViewById(R.id.txtId);
        txtTitle = findViewById(R.id.txtTitle);
        txtPrice = findViewById(R.id.txtPrice);
        btnUpdateBook = findViewById(R.id.btnUpdateBook);
        btnSearch = findViewById(R.id.btnSearchById);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = txtBookId.getText().toString();
                if(s != null && s.length() > 0)
                {
                    BookDBHandler db = new BookDBHandler(UpdateActivity.this);
                    BookEntity b = db.findById(Integer.parseInt(s));
                    if(b != null){
                        txtTitle.setText(b.getTitle());
                        txtPrice.setText(String.valueOf(b.getPrice()));
                    }
                }
            }
        });
        btnUpdateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDBHandler db = new BookDBHandler(UpdateActivity.this);
                BookEntity b = new BookEntity();
                b.setBookId(Integer.parseInt(txtBookId.getText().toString()));
                b.setTitle(txtTitle.getText().toString());
                b.setPrice(Integer.parseInt(txtPrice.getText().toString()));
                db.update(b);

                Intent t = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(t);
            }
        });
    }
}
