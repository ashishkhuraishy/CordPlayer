package com.example.cordplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private EditText userName, songName, artistName, albumName;
    private Button buttonField;

    private String user, song, artist, album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userName = (EditText) findViewById(R.id.userName);
        songName = (EditText) findViewById(R.id.songName);
        artistName = (EditText) findViewById(R.id.artistName);
        albumName = (EditText) findViewById(R.id.albumName);

        Button buttonField = (Button) findViewById(R.id.buttonView);



      buttonField.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
               user = userName.getText().toString();
               song = songName.getText().toString();
               artist = artistName.getText().toString();
               album = albumName.getText().toString();

               mDatabase = FirebaseDatabase.getInstance().getReference(user);
               mDatabase.child("Song").setValue(song);
               mDatabase.child("Artist").setValue(artist);
               mDatabase.child("Album").setValue(album);
           }
       });

    }

}
