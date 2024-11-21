package com.example.justnote;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.justnote.databinding.EachItemBinding;

import javax.security.auth.callback.Callback;

public class NoteAdapter extends ListAdapter<Note,NoteAdapter.ViewHolder> { // here we create a note adapter
    // in this adapter all item remain same if you add oor delete someone
    public NoteAdapter() { // here we create a constructor
    super(Callback);
    }

    private static final DiffUtil.ItemCallback<Note> Callback = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTittle().equals(newItem.getTittle()) // here we check if the data is same or not
                    && oldItem.getDisp().equals(newItem.getDisp());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_item,parent,false); // here we inflate the layout
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { // here we bind the data

        Note note = getItem(position);
        holder.binding.titleRv.setText(note.getTittle());
        holder.binding.dispRv.setText(note.getDisp());
    }

    public Note getNote(int position) // here we get the note
    {
        return getItem(position); // here we return the note
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        EachItemBinding binding;
        public ViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = EachItemBinding.bind(itemView);
        }
    }
}
