package com.example.projectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.submit_button);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Toast.makeText(MainActivity.this, "Trying to login", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
