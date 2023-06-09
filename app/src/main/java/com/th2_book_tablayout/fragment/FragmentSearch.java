package com.th2_book_tablayout.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.th2_book_tablayout.R;
import com.th2_book_tablayout.UpdateDeleteActivity;
import com.th2_book_tablayout.adapter.RecyclerViewAdapter;
import com.th2_book_tablayout.db.Database;
import com.th2_book_tablayout.model.Book;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment {
    private EditText edTen, edTacgia;
    private Button btnSearch, btnSearch2, btnStatistic;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edTen = view.findViewById(R.id.edTen);
        edTacgia = view.findViewById(R.id.edTacgia);
        btnSearch = view.findViewById(R.id.btnSearch);
        btnSearch2 = view.findViewById(R.id.btnSearch2);
        btnStatistic = view.findViewById(R.id.btnGetStatistic);
        recyclerView = view.findViewById(R.id.recyclerViewFragmentSearch);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<>());
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));

        // Search
//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Database db = new Database(getActivity());
//                String sFrom = edFrom.getText().toString().trim();
//                String sTo = edTo.getText().toString().trim();
//                List<Book> listBook = db.findBooksByPrice(sFrom, sTo);
//                recyclerViewAdapter.setListBook(listBook);
//            }
//        });
        //SearchByTen
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db = new Database(getActivity());
                String tenSach = edTen.getText().toString().trim();
                List<Book> listBook = db.searchBookByTen(tenSach);
                recyclerViewAdapter.setListBook(listBook);
            }
        });
        btnSearch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db = new Database(getActivity());
                String tacGia = edTacgia.getText().toString().trim();
                List<Book> listBook = db.searchBookByTacgia(tacGia);
                recyclerViewAdapter.setListBook(listBook);
            }
        });
        // Statistic
//        btnStatistic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Database db = new Database(getActivity());
//                List<Book> listBook = db.getStatistic();
//                recyclerViewAdapter.setListBook(listBook);
//            }
//        });
        btnStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db = new Database(getActivity());
                List<Book> listBook = db.getStatistic();
                recyclerViewAdapter.setListBook(listBook);
            }
        });

        recyclerViewAdapter.setItemClickListener(new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
                intent.putExtra("book", book);
                startActivity(intent);
            }
        });
    }
}
