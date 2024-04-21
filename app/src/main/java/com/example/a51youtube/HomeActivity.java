package com.example.a51youtube;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView urlPlainText;
    private Button playButton;
    private Button addToPlaylistButton;
    private Button myPlayListButton;
    private URLDbHandler urlDbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // Initialize views
        urlPlainText = findViewById(R.id.urlPlainText);
        playButton = findViewById(R.id.playButton);
        addToPlaylistButton = findViewById(R.id.addToPlaylistButton);
        myPlayListButton = findViewById(R.id.myPlaylistButton);

        // Play Button Click Listener
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlPlainText.getText().toString();
                // Start VideoActivity with the provided URL
                Intent intent = new Intent(HomeActivity.this, VideoActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

        // Add to Playlist Button Click Listener
        addToPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = urlPlainText.getText().toString();

                // Check if the URL field is empty
                if (input.isEmpty()) {
                    Toast.makeText(HomeActivity.this, "Please enter a URL", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Add URL to the playlist
                URL url = new URL(input, USER.getInstance().getId());
                urlDbHandler = new URLDbHandler(HomeActivity.this);
                urlDbHandler.addNewURL(url);

                Toast.makeText(HomeActivity.this, "URL added to playlist", Toast.LENGTH_SHORT).show();
            }
        });

        // My Playlist Button Click Listener
        myPlayListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start PlaylistActivity
                Intent intent = new Intent(HomeActivity.this, PlaylistActivity.class);
                startActivity(intent);
            }
        });
    }
}
