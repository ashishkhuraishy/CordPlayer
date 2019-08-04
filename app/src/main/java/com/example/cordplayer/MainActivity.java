package com.example.cordplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText userName;
    private Button buttonField;
    String prefUser;
    static String user = "";
    SharedPreferences sharedPreferences;
    TextInputLayout displayNameInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
        userName = findViewById(R.id.userName);

        buttonField = findViewById(R.id.buttonView);
        displayNameInput = findViewById(R.id.displayTextInput);


        prefUser = sharedPreferences.getString("user_id", user);

        if(prefUser.length()< 1){
            Toast.makeText(MainActivity.this, "No User Name", Toast.LENGTH_LONG).show();
            buttonField.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    user = userName.getText().toString();
                    sharedPreferences.edit().putString("user_id", user).apply();

                    if(user.length() == 0){
                        displayNameInput.setError("Please Input A Name");
                    }else {
                        Toast.makeText(MainActivity.this, "Welcome " + user, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, SongsListActivity.class);
                        startActivity(intent);
                    }
//               mDatabase = FirebaseDatabase.getInstance().getReference(user);
//               mDatabase.child("Song").setValue("SongName");
//               mDatabase.child("Artist").setValue("ArtistName");
//               mDatabase.child("Album").setValue("AlbumName");
                }
            });

        }else {
            Intent intent = new Intent(MainActivity.this, SongsListActivity.class);
            user = prefUser;
            startActivity(intent);
        }
    }


}
