package com.example.cordplayer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SongsListActivity extends AppCompatActivity {

    ArrayList<SongInfo> mSongs = new ArrayList<SongInfo>();
    RecyclerView recyclerView;
    SongInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        recyclerView = (RecyclerView) findViewById(R.id.songList);

        adapter = new SongInfoAdapter(this, mSongs);
    }
}
