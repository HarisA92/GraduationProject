package com.example.user.graduationproject.Bjelasnica;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.user.graduationproject.Bjelasnica.Adapters.ViewPagerAdapter;
import com.example.user.graduationproject.Bjelasnica.Utils.SkiResortHolder;
import com.example.user.graduationproject.R;

public class Main extends AppCompatActivity {
    final int[] ICONS = new int[]{
            R.drawable.report,
            R.drawable.weatherwhite,
            R.drawable.webcam,
            R.drawable.trailmap,
            R.drawable.liftticket,
            R.drawable.gallery,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Report"));
        tabLayout.addTab(tabLayout.newTab().setText("Weather"));
        tabLayout.addTab(tabLayout.newTab().setText("Webcams"));
        tabLayout.addTab(tabLayout.newTab().setText("Trail Map"));
        tabLayout.addTab(tabLayout.newTab().setText("Lift Tickets"));
        tabLayout.addTab(tabLayout.newTab().setText("Gallery"));
        tabLayout.setTabMode(TabLayout.GRAVITY_FILL);
        tabLayout.getTabAt(0).setIcon(ICONS[0]);
        tabLayout.getTabAt(1).setIcon(ICONS[1]);
        tabLayout.getTabAt(2).setIcon(ICONS[2]);
        tabLayout.getTabAt(3).setIcon(ICONS[3]);
        tabLayout.getTabAt(4).setIcon(ICONS[4]);
        tabLayout.getTabAt(5).setIcon(ICONS[5]);

        Main.this.setTitle(SkiResortHolder.getSkiResort().getMountain().getValue());

        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

}
