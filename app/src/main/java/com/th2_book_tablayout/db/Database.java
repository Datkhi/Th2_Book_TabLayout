package com.th2_book_tablayout.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.th2_book_tablayout.model.Book;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bookDB2";
    private static final int DATABASE_VERSION = 1;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE books(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ten TEXT," +
                "tacgia TEXT," +
                "phamvi TEXT," +
                "doituong TEXT," +
                "danhgia TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public long addBook(Book book){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", book.getTen());
        values.put("tacgia", book.getTacgia());
        values.put("phamvi", book.getPhamvi());
        values.put("doituong", book.getDoituong());
        values.put("danhgia", book.getDanhgia());
        long result = sqLiteDatabase.insert("books", null, values);
        sqLiteDatabase.close();
        return result;
    }

    public List<Book> getAllBook(){
        List<Book> listBook = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase =getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("books", null, null,
                null, null, null, null);
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String tacgia = cursor.getString(2);
            String phamvi = cursor.getString(3);
            String doituong = cursor.getString(4);
            String danhgia = cursor.getString(5);
            Book book = new Book(id,ten,tacgia,phamvi,doituong,danhgia);
            listBook.add(book);
        }
        return listBook;
    }
    public long updateBook(Book book){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", book.getTen());
        values.put("tacgia", book.getTacgia());
        values.put("phamvi", book.getPhamvi());
        values.put("doituong", book.getDoituong());
        values.put("danhgia", book.getDanhgia());
        long result = sqLiteDatabase.update("books",
                values,"id=?",
                new String[]{book.getId()+""});
        sqLiteDatabase.close();
        return result;
    }

    public long deleteBook(int bookId){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long result = sqLiteDatabase.delete("books", "id=?",
                new String[]{bookId+""});
        sqLiteDatabase.close();
        return result;
    }

//    public List<Book> findBooksByPrice(String startPrice, String endPrice){
//        List<Book> listBook = new ArrayList<>();
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.query("books",null,"price BETWEEN ? AND ?"
//                , new String[]{startPrice, endPrice},null,null,null);
//        while(cursor!=null && cursor.moveToNext()){
//            int id = cursor.getInt(0);
//            String name = cursor.getString(1);
//            String author = cursor.getString(2);
//            String publishDate = cursor.getString(3);
//            String publisher = cursor.getString(4);
//            String price = cursor.getFloat(5)+"";
//            Book book = new Book(id,name,author,publishDate,publisher,price);
//            listBook.add(book);
//        }
//        return listBook;
//    }
//    public List<Book> getStatistic(){
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        List<Book> listBook = new ArrayList<>();
//        Cursor cursor = sqLiteDatabase.query("books",new String[]{"id","name","author","publishDate","publisher","MAX(price) AS price"},
//                null, null,"publisher",null,"price DESC");
//        while(cursor!=null && cursor.moveToNext()){
//            int id = cursor.getInt(0);
//            String name = cursor.getString(1);
//            String author = cursor.getString(2);
//            String publishDate = cursor.getString(3);
//            String publisher = cursor.getString(4);
//            String price = cursor.getFloat(5)+"";
//            Book book = new Book(id,name,author,publishDate,publisher,price);
//            listBook.add(book);
//        }
//        return listBook;
//    }
    public List<Book> searchBookByTen(String s){
    List<Book> list = new ArrayList<>();
    String where = "ten like ?";
    String[] args = {"%"+s+"%"};
    SQLiteDatabase st = getReadableDatabase();
    Cursor cursor = st.query("books", null, where, args, null, null, null);
    while (cursor!=null && cursor.moveToNext()){
        int id = cursor.getInt(0);
        String ten = cursor.getString(1);
        String tacgia = cursor.getString(2);
        String phamvi = cursor.getString(3);
        String doituong = cursor.getString(4);
        String danhgia = cursor.getString(5);
        list.add(new Book(id, ten, tacgia, phamvi, doituong, danhgia));
    }
    if(cursor != null) {
        cursor.close();
    }
    return list;
}
}
