package com.example.ngan.book;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BookDBHandler extends SQLiteOpenHelper {

    private final static String DB_NAME = "book.db";
    private final static int DB_VERSION = 2;
    private final static String TBL_BOOK = "book";


    public BookDBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TBL_BOOK + " (_id integer primary key AUTOINCREMENT,  bookId integer," +
            "title varchar(200), price integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_BOOK);
    }

    public Cursor getBooksCursor(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TBL_BOOK,new String[]{"_id","bookId","title","price"},null,
                null,null,null,null);
    }
    public BookEntity findById(Integer bookId){
        BookEntity b = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur =  db.query(TBL_BOOK,new String[]{"bookId","title","price"},"bookId=?",
                new String[]{String.valueOf(bookId)},null,null,null);
        if(cur != null && cur.getCount() > 0){
            if (cur.moveToNext()){
                 b = new BookEntity();
                b.setBookId(cur.getInt(0));
                b.setTitle(cur.getString(1));
                b.setPrice(cur.getInt(2));
            }
        }
        return b;
    }
    public List<BookEntity> findByTitle(String tit ){
        List<BookEntity> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur =  db.query(TBL_BOOK,new String[]{"bookId","title","price"},"title LIKE ?",
                new String[]{"%"+tit+"%"},null,null,null);
        if(cur != null && cur.getCount() > 0){
            while (cur.moveToNext()){
                BookEntity b = new BookEntity();
                b = new BookEntity();
                b.setBookId(cur.getInt(0));
                b.setTitle(cur.getString(1));
                b.setPrice(cur.getInt(2));
                result.add(b);
            }
        }
        return result;
    }
    public List<BookEntity> getBooks(){
        List<BookEntity> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur =  db.query(TBL_BOOK,new String[]{"bookId","title","price"},null,
                null,null,null,null);
        if(cur != null && cur.getCount() > 0){
            while (cur.moveToNext()){
                BookEntity b = new BookEntity();
                b.setBookId(cur.getInt(0));
                b.setTitle(cur.getString(1));
                b.setPrice(cur.getInt(2));
                result.add(b);
            }
        }
        return result;
    }

    public void add(BookEntity b){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues v = new ContentValues();
        v.put("bookId", b.getBookId());
        v.put("title",b.getTitle());
        v.put("price",b.getPrice());
        db.insert(TBL_BOOK,null,v);
    }

    public void update(BookEntity b){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues v = new ContentValues();
        v.put("title",b.getTitle());
        v.put("price",b.getPrice());
        db.update(TBL_BOOK,v,"bookId=?",new String[]{String.valueOf(b.getBookId())});
    }
    public void delete(int bookId){
        SQLiteDatabase db = this.getReadableDatabase();

        db.delete(TBL_BOOK, "bookId=?",new String[]{String.valueOf(bookId)});
    }
}
