package com.example.cordplayer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;


import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;

public class SongsListActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    ArrayList<SongInfo> mSongs = new ArrayList<>();
    ListView listView;
    SongInfoAdapter adapter;
    TextView songNameNp, playBtnTxt;
    MaterialCardView playBtn;
    MediaPlayer mediaPlayer;
    LinearLayout nowPlayingView;
    String songName, artistName, albumName, userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        Bundle bundle = getIntent().getExtras();
        userId = bundle.getString("userId");


        adapter = new SongInfoAdapter(this, mSongs);
        listView = (ListView) findViewById(R.id.songList);
        listView.setAdapter(adapter);
        mDatabase = FirebaseDatabase.getInstance().getReference(userId);
        songNameNp = (TextView) findViewById(R.id.songNameNP);
        playBtn = (MaterialCardView) findViewById(R.id.playButton);
        playBtnTxt = (TextView) findViewById(R.id.playBtnTxt);
        nowPlayingView = (LinearLayout) findViewById(R.id.nowPlaying);
        mediaPlayer = new MediaPlayer();

        checkPermission();

        songNameNp.setText("Not Playing");
        playBtn.setVisibility(View.GONE);

        addToDataBase("Not Playing", "Not Playing", "Not Playing");

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    addToDataBase("Not Playing", "Not Playing", "Not Playing");
                    playBtnTxt.setText("Play");
                }else {
                    mediaPlayer.start();
                    addToDataBase(songName, artistName, albumName);
                    playBtnTxt.setText("Pause");
                }
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                SongInfo currentPosition = (SongInfo) adapterView.getItemAtPosition(i);

                songName = currentPosition.getmSongName();
                artistName = currentPosition.getmArtistName();
                albumName = currentPosition.getmAlbum();

                addToDataBase(songName, artistName, albumName);

                songNameNp.setText(songName);
                playBtn.setVisibility(View.VISIBLE);

                nowPlaying(currentPosition.getmUrl());
            }
        });


    }

    /**Function To add Details Into Database**/

    void addToDataBase(String songName, String artistName, String albumName){
        mDatabase.child("Song").setValue(songName);
        mDatabase.child("Artist").setValue(artistName);
        mDatabase.child("Album").setValue(albumName);
    }


    private void nowPlaying(String url){

        if(mediaPlayer.isPlaying()){
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    /*Functions To Check The user permission For Readig External Storage*/


    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
                return;
            }loadSongs();
        }
        loadSongs();
    }


    private void loadSongs() {
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);

        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    String[] name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)).split(".mp3");
                    String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                    String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));

                    SongInfo s = new SongInfo(name[0], artist, album, url);
                    mSongs.add(s);

                }while (cursor.moveToNext());
            }
            cursor.close();
            adapter = new SongInfoAdapter(this, mSongs);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == 123) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadSongs();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
                checkPermission();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}

