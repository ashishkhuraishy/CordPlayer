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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private TextInputEditText userName;
    private Button buttonField;

    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userName = (TextInputEditText) findViewById(R.id.userName);

        buttonField = (Button) findViewById(R.id.buttonView);



      buttonField.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
               user = userName.getText().toString();

               Toast.makeText(MainActivity.this, "Welcome"+user, Toast.LENGTH_LONG).show();


//               mDatabase = FirebaseDatabase.getInstance().getReference(user);
//               mDatabase.child("Song").setValue("SongName");
//               mDatabase.child("Artist").setValue("ArtistName");
//               mDatabase.child("Album").setValue("AlbumName");
           }
       });

    }

}
