package com.example.ngan.book;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class BookAdapter extends CursorAdapter {
    public BookAdapter(Context context, Cursor c) {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_book,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView lblBookId = (TextView) view.findViewById(R.id.lblBookId);
        TextView lblTitle = (TextView) view.findViewById(R.id.lblTitle);
        TextView lblPrice = (TextView) view.findViewById(R.id.lblPrice);

        int bookId = cursor.getInt(cursor.getColumnIndexOrThrow("bookId"));
        String title  = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        int price = cursor.getColumnIndexOrThrow("price");
        Log.d("Book Adapter", String.valueOf(price));
        lblBookId.setText(String.valueOf(bookId));
        lblTitle.setText(title);
        lblPrice.setText(String.valueOf(price));
    }
}
