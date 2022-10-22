package com.example.quran_tracker_1.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quran_tracker_1.R;
import com.example.quran_tracker_1.adapters.RecitationsFragmentRecyclerAdapter;

public class RecitationsFragment extends Fragment {

    View view;

    /* Layout variables */
    RecyclerView recyclerView;
    RecitationsFragmentRecyclerAdapter recitationsFragmentRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = (ViewGroup) inflater.inflate(R.layout.fragment_recitations, container, false);

        /* Layout variables */
        onCreate_recyclerView();

        return view;
    }

    private void onCreate_recyclerView(){
        recyclerView = view.findViewById(R.id.recitationsFragment_recycler);
        recitationsFragmentRecyclerAdapter = new RecitationsFragmentRecyclerAdapter(new String[]{"hello", "recitation",
        "Longer names make dots"});
        recyclerView.setAdapter(recitationsFragmentRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

}
