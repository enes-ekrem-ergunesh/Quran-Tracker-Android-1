package com.example.quran_tracker_1.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quran_tracker_1.ItemViewModel;
import com.example.quran_tracker_1.R;
import com.example.quran_tracker_1.adapters.ChaptersFragmentRecyclerAdapter;
import com.example.quran_tracker_1.room.AppDatabase;
import com.example.quran_tracker_1.room.Chapter;

import java.util.List;
import java.util.Objects;

public class ChaptersFragment extends Fragment {

    /* Backend variables */
    View view;
    private ItemViewModel viewModel;


    /* RecyclerView variables */
    RecyclerView recyclerView;
    ChaptersFragmentRecyclerAdapter chaptersFragmentRecyclerAdapter;
    List<Chapter> chapters;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = (ViewGroup) inflater.inflate(R.layout.fragment_chapters, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);


        /* Layout variables */
        onCreate_recyclerView();

        return view;
    }

    private void onCreate_recyclerView(){
        recyclerView = view.findViewById(R.id.chaptersFragment_recycler);
        new Thread(this::getData).start();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("CHAPTER FRAGMENT", "RESUMING...........");

        // Get added chapter data from MainActivity using ItemViewModel
        viewModel.getSelectedItem().observe(this, item -> {
            if(item != null &&
                    ( chapters.size()==0 || item.getInt("chapter_id") != chapters.get(chapters.size()-1).id)){
                Log.d("onResume", "item status: "+item);

                Chapter chapter = new Chapter(
                        item.getString("chapter_name"),
                        item.getInt("chapter_number"),
                        item.getBoolean("chapter_isCompleted")
                        );
                chapter.id = item.getInt("chapter_id");

                chapters.add(chapter);
                Objects.requireNonNull(recyclerView.getAdapter()).notifyItemInserted(recyclerView.getAdapter().getItemCount());
            }
        });

        // After adding the chapter set view model item to null to prevent recall onResume
        viewModel.selectItem(null);
    }



    // ROOM
    private void getData(){
        chapters = AppDatabase.getInstance(getContext()).chapterDao().getAll();
        chaptersFragmentRecyclerAdapter = new ChaptersFragmentRecyclerAdapter(getContext(), chapters);
        requireActivity().runOnUiThread(() -> {
            recyclerView.setAdapter(chaptersFragmentRecyclerAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        });
    }


}