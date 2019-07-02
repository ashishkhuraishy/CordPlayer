package com.example.cordplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private TextInputEditText userName;
    private Button buttonField;

    private static String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userName = (TextInputEditText) findViewById(R.id.userName);

        buttonField = (Button) findViewById(R.id.buttonView);
        final TextInputLayout displayNameInput = (TextInputLayout) findViewById(R.id.displayTextInput);



      buttonField.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
               user = userName.getText().toString();

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

    }

    public static String userName(){
        return user;
    }

}
