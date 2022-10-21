package com.example.quran_tracker_1.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.quran_tracker_1.fragments.ChaptersFragment;
import com.example.quran_tracker_1.fragments.RecitationsFragment;

public class MainViewPagerAdapter extends FragmentStateAdapter {


    public MainViewPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return new ChaptersFragment();
        }
        return new RecitationsFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
