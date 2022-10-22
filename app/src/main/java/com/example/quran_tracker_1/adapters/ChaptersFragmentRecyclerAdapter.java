package com.example.quran_tracker_1.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quran_tracker_1.R;
import com.example.quran_tracker_1.room.AppDatabase;
import com.example.quran_tracker_1.room.Chapter;

import java.util.List;
import java.util.Locale;


public class ChaptersFragmentRecyclerAdapter extends RecyclerView.Adapter<ChaptersFragmentRecyclerAdapter.ViewHolder>{

    Context context;
    public List<Chapter> chapters;

    /* Backend variables */
    Toast toast;

    /* Room variables */
    Thread threadMark;
    int markID;
    boolean markValue;
    long markPID = -1;
    Thread threadDelete;
    int deleteID;
    long deletePID = -1;

    public ChaptersFragmentRecyclerAdapter(Context context, List<Chapter> chapters) {
        this.context = context;
        this.chapters = chapters;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_fragment_chapters, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.checkBox.setChecked(chapters.get(position).isCompleted);
        holder.name.setText(chapters.get(position).name);
        holder.chapterNo.setText( String.format(Locale.US, "%d", chapters.get(position).number) );
        Log.d("CFRA_onBindViewHolder", chapters.get(position).toString());

        // Mark record
        holder.checkBox.setOnCheckedChangeListener((compoundButton, b) -> mark(holder.getAdapterPosition(), b));

        // Delete record
        holder.delete.setOnClickListener(view -> {
            try{
                delete(chapters.indexOf(chapters.get(holder.getAdapterPosition())));
            } catch (Exception e){
                Log.d("Delete chapter", e.getMessage());
            }
        });

    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        TextView name;
        TextView chapterNo;
        Button delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.CFRV_checkBox);
            name = itemView.findViewById(R.id.CFRV_textView_name);
            chapterNo = itemView.findViewById(R.id.CFRV_textView_chapterNo);
            delete = itemView.findViewById(R.id.CFRV_button_delete);
        }
    }

    // ROOM
    private void mark(int position, boolean b){
        markID = chapters.get(position).id;
        markValue = b;

        // If thread is alive, toast message: "Marker is busy! Please wait..."
        for (Thread t : Thread.getAllStackTraces().keySet()){
            if(t.getId()==markPID){
                if(t.isAlive()){
                    toast(context.getString(R.string.recycler_mark_threadIsAlive));
                    return;
                }
            }
        }

        threadMark = new Thread(this::threadMark);
        threadMark.start();
        markPID = threadMark.getId();

        chapters.get(position).isCompleted = b;
    }

    private void threadMark(){
        AppDatabase.getInstance(context).chapterDao().setCompleted(markID, markValue);
        AppDatabase.getInstance(context).pageDao().setCompletedByChapterId(markID, markValue);
    }

    private void delete(int position){
        deleteID = chapters.get(position).id;

        // If thread is alive, toast message: "Deleter is busy! Please wait..."
        for (Thread t : Thread.getAllStackTraces().keySet()){
            if(t.getId()==deletePID){
                if(t.isAlive()){
                    toast(context.getString(R.string.recycler_delete_threadIsAlive));
                    return;
                }
            }
        }

        threadDelete = new Thread(this::threadDelete);
        threadDelete.start();
        deletePID = threadDelete.getId();

        chapters.remove(position);
        notifyItemRemoved(position);

    }

    private void threadDelete(){
        AppDatabase.getInstance(context).chapterDao().deleteByID(deleteID);
        AppDatabase.getInstance(context).pageDao().deleteAllByChapterID(deleteID);
    }

    private void toast(String message){
        if(toast != null)
            toast.cancel();
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}






















