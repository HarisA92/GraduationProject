package com.bhplanine.user.graduationproject.utils;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.bhplanine.user.graduationproject.BuildConfig;
import com.bhplanine.user.graduationproject.R;
import com.bhplanine.user.graduationproject.activities.HomeActivity;
import com.bhplanine.user.graduationproject.fragments.navigationFragments.expandListFragments.ContentFragment;

public class FragmentNavigationManager implements NavigationManager {

    private static FragmentNavigationManager sInstance;

    private FragmentManager mFragmentManager;

    public static FragmentNavigationManager obtain(HomeActivity activity) {
        if (sInstance == null) {
            sInstance = new FragmentNavigationManager();
        }
        sInstance.configure(activity);
        return sInstance;
    }

    private void configure(HomeActivity activity) {
        mFragmentManager = activity.getSupportFragmentManager();
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fm = mFragmentManager;

        @SuppressLint("CommitTransaction")
        FragmentTransaction ft = fm.beginTransaction()
                .replace(R.id.fragment_container, fragment);

        ft.addToBackStack(null);

        if (!BuildConfig.DEBUG) {
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }

        fm.executePendingTransactions();
    }

    @Override
    public void showFragmentAccommodation(String title) {
        showFragment(ContentFragment.newInstance(title));
    }
}
