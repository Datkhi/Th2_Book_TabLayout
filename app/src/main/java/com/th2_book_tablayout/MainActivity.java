package com.th2_book_tablayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.th2_book_tablayout.adapter.FragmentAdapter;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    private FragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        adapter = new FragmentAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });
    }

    private void initView() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        fab = findViewById(R.id.fab);
    }
}