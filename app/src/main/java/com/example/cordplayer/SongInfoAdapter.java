package com.example.cordplayer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SongInfoAdapter extends ArrayAdapter<SongInfo> {

    public SongInfoAdapter(Activity context, ArrayList<SongInfo> songInfos) {
        super(context, 0, songInfos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.song_list_details, parent, false);
        }

        SongInfo currentPosition = getItem(position);

        TextView songName = (TextView) listItemView.findViewById(R.id.songName);
        songName.setText(currentPosition.getmSongName());

        TextView artistName = (TextView) listItemView.findViewById(R.id.artistName);
        artistName.setText(currentPosition.getmArtistName());

        return listItemView;
    }
}