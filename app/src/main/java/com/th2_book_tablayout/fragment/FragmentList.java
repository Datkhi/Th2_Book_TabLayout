package com.th2_book_tablayout.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import java.util.List;

public class FragmentList extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);

        // Lấy list book từ csdl Sqlite
        Database db = new Database(getActivity());
        List<Book> allBook = db.getAllBook();

        // Hiển thị list
        recyclerViewAdapter = new RecyclerViewAdapter(allBook);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));

        // Click vào từng sách để update hoặc delete
        recyclerViewAdapter.setItemClickListener(new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                Intent intentOpenUpdateDeleteActivity = new Intent(getActivity(), UpdateDeleteActivity.class);
                intentOpenUpdateDeleteActivity.putExtra("book", book);
                startActivity(intentOpenUpdateDeleteActivity);
            }
        });
    }
}
