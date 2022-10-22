package com.example.quran_tracker_1.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quran_tracker_1.R;

public class RecitationsFragmentRecyclerAdapter extends RecyclerView.Adapter<RecitationsFragmentRecyclerAdapter.ViewHolder> {

    Context context;
    String[] array;

    public RecitationsFragmentRecyclerAdapter(String[] array) {
        this.array = array;
        Log.d("RFRA", array.toString());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_fragment_recitations, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(array[position]);
        Log.d("RFRA_onBindViewHolder", array[position]);
    }

    @Override
    public int getItemCount() {
        return array.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.RFRV_textView_name);
        }
    }
}
