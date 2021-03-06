package com.bhplanine.user.graduationproject.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.bhplanine.user.graduationproject.R;
import com.bhplanine.user.graduationproject.adapters.TabLayoutAdapter;
import com.bhplanine.user.graduationproject.models.SkiResortHolder;
import com.bhplanine.user.graduationproject.utils.ZoomOutPageTransformer;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private final int[] ICONS = new int[]{
            R.drawable.report,
            R.drawable.weatherwhite,
            R.drawable.webcam,
            R.drawable.trailmap,
            R.drawable.liftticket,
            R.drawable.gallery,
    };

    TabLayoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ViewPager viewPager = findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Report"));
        tabLayout.addTab(tabLayout.newTab().setText("Weather"));
        tabLayout.addTab(tabLayout.newTab().setText("Webcams"));
        tabLayout.addTab(tabLayout.newTab().setText("Trail Map"));
        tabLayout.addTab(tabLayout.newTab().setText("Lift Tickets"));
        tabLayout.addTab(tabLayout.newTab().setText("Gallery"));
        tabLayout.setTabMode(TabLayout.GRAVITY_FILL);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(ICONS[0]);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(ICONS[1]);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(ICONS[2]);
        Objects.requireNonNull(tabLayout.getTabAt(3)).setIcon(ICONS[3]);
        Objects.requireNonNull(tabLayout.getTabAt(4)).setIcon(ICONS[4]);
        Objects.requireNonNull(tabLayout.getTabAt(5)).setIcon(ICONS[5]);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        MainActivity.this.setTitle(SkiResortHolder.getSkiResort().getMountain().getValue());

        adapter = new TabLayoutAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
