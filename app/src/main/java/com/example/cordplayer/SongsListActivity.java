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
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SongsListActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    ArrayList<SongInfo> mSongs = new ArrayList<>();
    ListView listView;
    SongInfoAdapter adapter;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);
        adapter = new SongInfoAdapter(this, mSongs);
        listView = (ListView) findViewById(R.id.songList);
        listView.setAdapter(adapter);

        checkPermission();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                SongInfo currentPosition = (SongInfo) adapterView.getItemAtPosition(i);

                mDatabase = FirebaseDatabase.getInstance().getReference(MainActivity.userName());
                mDatabase.child("Song").setValue(currentPosition.getmSongName());
                mDatabase.child("Artist").setValue(currentPosition.getmArtistName());
                mDatabase.child("Album").setValue(currentPosition.getmAlbum());
            }
        });


    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
                return;
            }

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
                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                    String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));

                    SongInfo s = new SongInfo(name, artist, album, uri);
                    mSongs.add(s);

                }while (cursor.moveToNext());
            }
            cursor.close();
            adapter = new SongInfoAdapter(this, mSongs);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {

        switch (requestCode){
            case 123:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    loadSongs();
                }else{
                    Toast.makeText(this,"Permission Denied", Toast.LENGTH_LONG ).show();
                    checkPermission();
                }break;

                default:
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}

