package com.example.a51youtube;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlaylistActivity extends AppCompatActivity implements RecyclerViewAdapter.clickListener {

    private RecyclerView playlistRecyclerView;
    private URLDbHandler urlDbHandler;

    private List<URL> urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_playlist);

        // Initialize URLDbHandler and get all URLs by user ID
        urlDbHandler = new URLDbHandler(PlaylistActivity.this);
        urls = urlDbHandler.getAllURLSByUserId(USER.getInstance().getId());

        // Set up RecyclerView
        playlistRecyclerView = findViewById(R.id.playlistRecyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(urls, this, this);
        playlistRecyclerView.setAdapter(adapter);
        playlistRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // RecyclerView item click
    @Override
    public void onClick(int position) {
        // Start VideoActivity with the URL
        Intent intent = new Intent(PlaylistActivity.this, VideoActivity.class);
        intent.putExtra("url", urls.get(position).getURL());
        startActivity(intent);
    }
}
