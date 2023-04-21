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

import androidx.appcompat.app.AppCompatActivity;


import com.th2_book_tablayout.db.Database;
import com.th2_book_tablayout.model.Book;


public class UpdateDeleteActivity extends AppCompatActivity {
    private EditText eTen;
    private Spinner spTacgia, spPhamvi;
    private RadioButton r1, r2, r3;
    private RatingBar rating;
    private Button btnUpdate, btnCancel, btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();

        // Hiện ra thông tin của book cần update
        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("book");
        eTen.setText(book.getTen());
        for(int i=0;i<spTacgia.getCount();i++){
            // Duyệt qua list item trong spinner tacgia xem item nào trùng với tacgia của book cần sửa thì hiện item ý được chọn trong spinner
            if(book.getTacgia().equals(spTacgia.getItemAtPosition(i))){
                spTacgia.setSelection(i);
                break;
            }
        }
        for(int i=0;i<spPhamvi.getCount();i++){
            // Duyệt qua list item trong spinner phamvi xem item nào trùng với phamvi của book cần sửa thì hiện item ý được chọn trong spinner
            if(book.getPhamvi().equals(spPhamvi.getItemAtPosition(i))){
                spPhamvi.setSelection(i);
                break;
            }
        }
        String dt = book.getDoituong();
        if(dt.contains("CNTT")) r1.setChecked(true);
        if(dt.contains("VT")) r2.setChecked(true);
        if(dt.contains("ĐT")) r3.setChecked(true);
        String rt = book.getDanhgia();
        rating.setRating(Float.parseFloat(rt));

        // Bấm nút update để đóng gói thông tin trên form vào 1 đối tượng Book rồi lưu xuống cơ sở dữ liệu
        // Và trở về main activity
        btnUpdate.setOnClickListener(view -> {
            String ten = eTen.getText().toString();
            String tacGia = spTacgia.getSelectedItem().toString();
            String phamVi = spPhamvi.getSelectedItem().toString();
            String doiTuong = "";
            if(r1.isChecked()) doiTuong += r1.getText().toString();
            if(r2.isChecked()) doiTuong += r2.getText().toString();
            if(r3.isChecked()) doiTuong += r3.getText().toString();
            String danhGia = rating.getRating()+"";

            Book bookToUpdate = new Book(book.getId(),ten,tacGia,phamVi,doiTuong,danhGia);

            Database db = new Database(UpdateDeleteActivity.this);
            db.updateBook(bookToUpdate);

            Intent intentToMainActivity = new Intent(UpdateDeleteActivity.this, MainActivity.class);
            startActivity(intentToMainActivity);
        });

        btnDelete.setOnClickListener(view -> {
            Database db = new Database(UpdateDeleteActivity.this);
            db.deleteBook(book.getId());

            Intent intentToMainActivity = new Intent(UpdateDeleteActivity.this, MainActivity.class);
            startActivity(intentToMainActivity);
        });

        btnCancel.setOnClickListener(view -> {
            Intent intentToMainActivity = new Intent(UpdateDeleteActivity.this, MainActivity.class);
            startActivity(intentToMainActivity);
        });
    }
    private void initView() {
        eTen = findViewById(R.id.eTen);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);

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