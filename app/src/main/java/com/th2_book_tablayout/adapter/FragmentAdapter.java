package com.th2_book_tablayout.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.th2_book_tablayout.fragment.FragmentInfo;
import com.th2_book_tablayout.fragment.FragmentList;
import com.th2_book_tablayout.fragment.FragmentSearch;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new FragmentInfo();
            case 2:
                return new FragmentSearch();
            default:
                return new FragmentList();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 1:
                title = "INFO";
                break;
            case 2:
                title = "SEARCH";
                break;
            default:
                title = "LIST";
                break;
        }
        return title;
    }
}
