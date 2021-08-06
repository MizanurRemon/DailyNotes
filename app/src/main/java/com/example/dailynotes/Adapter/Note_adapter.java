package com.example.dailynotes.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailynotes.Model.Notes.Notes_response;
import com.example.dailynotes.R;

import java.util.List;

public class Note_adapter extends RecyclerView.Adapter<Note_adapter.AppViewholder> {

    private List<Notes_response> notesList;
    private OnItemClickListener mListener;
    public OnItemDeleteClickListener deleteListener;

    public Note_adapter(List<Notes_response> notesList) {
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public Note_adapter.AppViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.notes_card, parent, false);
        return new Note_adapter.AppViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Note_adapter.AppViewholder holder, int position) {
        Notes_response notesResponse = notesList.get(position);
        holder.noteTitle.setText(notesResponse.getTitle());
        holder.noteTime.setText(notesResponse.getTime());
        holder.noteDate.setText(notesResponse.getDate());

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public interface OnItemDeleteClickListener {
        void OnItemDelete(int position);
    }

    public void setOnClickListener(Note_adapter.OnItemClickListener mListener, Note_adapter.OnItemDeleteClickListener deleteListener) {
        this.mListener = mListener;
        this.deleteListener = deleteListener;
    }

    public class AppViewholder extends RecyclerView.ViewHolder {
        TextView noteTitle, noteTime, noteDate;
        ImageView deleteButton;

        public AppViewholder(@NonNull View itemView) {
            super(itemView);

            noteTitle = itemView.findViewById(R.id.noteTitleID);
            noteTime = itemView.findViewById(R.id.noteTimeID);
            noteDate = itemView.findViewById(R.id.noteDateID);
            deleteButton = itemView.findViewById(R.id.deleteButtonID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.OnItemClick(position);
                        }
                    }
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (deleteListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            deleteListener.OnItemDelete(position);
                        }
                    }
                }
            });
        }
    }
}
