package com.example.cordplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SongInfoAdapter extends RecyclerView.Adapter<SongInfoAdapter.SongHolder> {

    ArrayList<SongInfo> mSongs;
    Context context;


    public SongInfoAdapter(Context context, ArrayList<SongInfo> mSongs) {
        this.context = context;
        this.mSongs = mSongs;
    }


    @Override
    public SongHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater.from(context).inflate(R.layout.song_list_details, parent, false);
        return new SongHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(SongHolder holder, int position) {
        SongInfo songInfo = mSongs.get(position);
        holder.songName.setText(songInfo.mSongName);
        holder.artistName.setText(songInfo.mArtistName);

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