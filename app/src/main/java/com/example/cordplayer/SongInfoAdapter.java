package com.example.cordplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SongInfoAdapter extends RecyclerView.Adapter<SongInfoAdapter.SongHolder> {

    ArrayList<SongInfo> mSongs;
    Context context;


    public SongInfoAdapter(Context context, ArrayList<SongInfo> mSongs) {
        this.context = context;
        this.mSongs = mSongs;
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater.from(context).inflate(R.layout.song_list_details, parent, false);
        return new SongHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder {

        TextView songName, artistName;

        public SongHolder(View itemView) {
            super(itemView);

            songName = (TextView) itemView.findViewById(R.id.songName);
            artistName = (TextView) itemView.findViewById(R.id.artistName);
        }
    }
}