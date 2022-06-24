package com.example.earn;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class catAdapter extends FragmentPagerAdapter {

    int tab_count;

    public catAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tab_count = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new categoryFragment();
    }

    @Override
    public int getCount() {
        return tab_count;
    }
}
