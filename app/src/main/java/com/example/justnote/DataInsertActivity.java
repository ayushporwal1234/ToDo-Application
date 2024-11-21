package com.example.justnote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.justnote.databinding.ActivityDataInsertActivtyBinding;

public class DataInsertActivity extends AppCompatActivity {

    ActivityDataInsertActivtyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataInsertActivtyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataInsertActivity.this, MainActivity.class);
                intent.putExtra("title", binding.insertTitle.getText().toString());
                intent.putExtra("dis", binding.insertDis.getText().toString());
                setResult(RESULT_OK,intent);
                finish();

                // Logic: this activity call by main activity then
                // it will set the data and then send data to main activity then it will closed
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DataInsertActivity.this, MainActivity.class));
    }
}