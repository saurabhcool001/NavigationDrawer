package com.example.expandable_navigation_drawer.fragment.navigation;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.expandable_navigation_drawer.BuildConfig;
import com.example.expandable_navigation_drawer.MainActivity;
import com.example.expandable_navigation_drawer.R;
import com.example.expandable_navigation_drawer.fragment.FragmentAction;
import com.example.expandable_navigation_drawer.fragment.FragmentComedy;
import com.example.expandable_navigation_drawer.fragment.FragmentDrama;
import com.example.expandable_navigation_drawer.fragment.FragmentMusical;
import com.example.expandable_navigation_drawer.fragment.FragmentThriller;

/**
 * @author msahakyan
 */

public class FragmentNavigationManager implements NavigationManager {

    private static FragmentNavigationManager sInstance;

    private FragmentManager mFragmentManager;
    private MainActivity mActivity;

    public static FragmentNavigationManager obtain(MainActivity activity) {
        if (sInstance == null) {
            sInstance = new FragmentNavigationManager();
        }
        sInstance.configure(activity);
        return sInstance;
    }

    private void configure(MainActivity activity) {
        mActivity = activity;
        mFragmentManager = mActivity.getSupportFragmentManager();
    }

    @Override
    public void showFragmentAction(String title) {
        showFragment(FragmentAction.newInstance(title), false);
    }

    @Override
    public void showFragmentComedy(String title) {
        showFragment(FragmentComedy.newInstance(title), false);
    }

    @Override
    public void showFragmentDrama(String title) {
        showFragment(FragmentDrama.newInstance(title), false);
    }

    @Override
    public void showFragmentMusical(String title) {
        showFragment(FragmentMusical.newInstance(title), false);
    }

    @Override
    public void showFragmentThriller(String title) {
        showFragment(FragmentThriller.newInstance(title), false);
    }

    private void showFragment(Fragment fragment, boolean allowStateLoss) {
        FragmentManager fm = mFragmentManager;

        @SuppressLint("CommitTransaction")
        FragmentTransaction ft = fm.beginTransaction()
            .replace(R.id.container, fragment);

        ft.addToBackStack(null);

        if (allowStateLoss || !BuildConfig.DEBUG) {
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }

        fm.executePendingTransactions();
    }
}
