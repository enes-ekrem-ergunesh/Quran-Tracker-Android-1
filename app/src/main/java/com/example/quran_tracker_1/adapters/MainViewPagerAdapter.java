package com.example.quran_tracker_1.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.quran_tracker_1.fragments.ChaptersFragment;
import com.example.quran_tracker_1.fragments.RecitationsFragment;

public class MainViewPagerAdapter extends FragmentStateAdapter {

    private static final int NUM_PAGES = 2;

    Fragment[] fragments;

    public MainViewPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragments = new Fragment[NUM_PAGES];
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(fragments[position] != null)
            return fragments[position];
        if (position == 1) {
            Fragment fragment = new ChaptersFragment();
            fragments[position] = fragment;
            return fragment;
        }
        Fragment fragment = new ChaptersFragment();
        fragments[position] = fragment;
        return new RecitationsFragment();
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }

    public Fragment getItemAt(int position){
        return fragments[position];
    }

}
