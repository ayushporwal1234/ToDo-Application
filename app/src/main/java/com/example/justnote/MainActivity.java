package com.example.justnote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.justnote.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding; // here we create a binding object
    private NoteViewModel noteViewModel; // here we create a note view model object
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); // here we inflate the layout
        setContentView(binding.getRoot());  // here we set the content view
        noteViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(NoteViewModel.class); // here we create a note view model object
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            // here we set the on click listener
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);
                startActivityForResult(intent, 1); // here we start the activity for result
            }
        });

        binding.RecyclerView.setLayoutManager(new LinearLayoutManager(this)); // here we set the layout manager
        binding.RecyclerView.setHasFixedSize(true); // here we set the has fixed size
        NoteAdapter adapter = new NoteAdapter();
        binding.RecyclerView.setAdapter(adapter); // here we set the adapter

        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                ((NoteAdapter) binding.RecyclerView.getAdapter()).submitList(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(adapter.getNote(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(binding.RecyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { // here we override the on activity result method
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1)// here we check the request code is 1 or not
        {

                String title = data.getStringExtra("title"); // here we get the data from data insert activity
                String dis = data.getStringExtra("dis");
                Note note = new Note(title,dis); // here we create a note object
            noteViewModel.insert(note); // here we insert the note in the database
            Toast.makeText(this, "Note Inserted", Toast.LENGTH_SHORT).show();
        }

    }
}