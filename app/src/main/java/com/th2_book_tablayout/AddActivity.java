package com.th2_book_tablayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.th2_book_tablayout.db.Database;
import com.th2_book_tablayout.model.Book;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    private EditText eTen;
    private Spinner spTacgia, spPhamvi;
    private RadioButton r1, r2, r3;
    private RatingBar rating;
    Button btUpdate, btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();

        //btUpdate de them sach
        btUpdate.setOnClickListener(view -> {
            String ten = eTen.getText().toString();
            String tacGia = spTacgia.getSelectedItem().toString();
            String phamVi = spPhamvi.getSelectedItem().toString();
            String doiTuong = "";
            if(r1.isChecked()) doiTuong += r1.getText().toString()+" ";
            if(r2.isChecked()) doiTuong += r2.getText().toString()+" ";
            if(r3.isChecked()) doiTuong += r3.getText().toString()+" ";
            doiTuong = doiTuong.trim();
            String danhGia = rating.getRating()+"";

//            Toast.makeText(this, ten+" "+" "+tacGia+" "+phamVi+" "+doiTuong+" "+danhGia, Toast.LENGTH_SHORT).show();
            Book newBook = new Book(ten, tacGia, phamVi, doiTuong, danhGia);
            Database db = new Database(AddActivity.this);
            db.addBook(newBook);

            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
        });

        btCancel.setOnClickListener(view -> {
            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void initView() {
        eTen = findViewById(R.id.eTen);

        btUpdate = findViewById(R.id.btUpdate);
        btCancel = findViewById(R.id.btCancel);

        spTacgia = findViewById(R.id.spTacgia);
        spPhamvi = findViewById(R.id.spPhamvi);
        spTacgia.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.tacgia)));
        spPhamvi.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.phamvi)));

        r1 = findViewById(R.id.radio1);
        r2 = findViewById(R.id.radio2);
        r3 = findViewById(R.id.radio3);

        rating = findViewById(R.id.rating);
    }
}