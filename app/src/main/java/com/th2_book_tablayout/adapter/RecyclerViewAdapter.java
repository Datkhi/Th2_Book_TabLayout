package com.th2_book_tablayout.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.th2_book_tablayout.R;
import com.th2_book_tablayout.model.Book;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    public interface ItemClickListener{
        void onItemClick(Book book);
    }
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    private List<Book> listBook;

    public void setListBook(List<Book> listBook) {
        this.listBook = listBook;
        notifyDataSetChanged();
    }

    public RecyclerViewAdapter(List<Book> listBook) {
        this.listBook = listBook;
    }

    class BookViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTen, tvTacgia, tvPhamvi, tvDoituong, tvDanhgia;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.tvTen);
            tvTacgia = itemView.findViewById(R.id.tvTacgia);
            tvPhamvi = itemView.findViewById(R.id.tvPhamvi);
            tvDoituong = itemView.findViewById(R.id.tvDoituong);
            tvDanhgia = itemView.findViewById(R.id.tvDanhgia);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BookViewHolder bookViewHolder = (BookViewHolder) holder;
        Book book = listBook.get(position);
        bookViewHolder.tvTen.setText(book.getTen());
        bookViewHolder.tvTacgia.setText(book.getTacgia());
        bookViewHolder.tvPhamvi.setText(book.getPhamvi());
        bookViewHolder.tvDoituong.setText(book.getDoituong());
        bookViewHolder.tvDanhgia.setText(book.getDanhgia());

        bookViewHolder.itemView.setOnClickListener(view -> {
            itemClickListener.onItemClick(book);
        });
    }

    @Override
    public int getItemCount() {
        return listBook!=null ? listBook.size() : 0;
    }
}
