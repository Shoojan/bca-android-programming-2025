package com.example.unit_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Find reference objects from the Views
        Button loginButton = findViewById(R.id.loginButton);
        EditText nameEditText = findViewById(R.id.nameEditText);

        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Grab the value from the EditText
                        String name = nameEditText.getText().toString();
                        if(name.trim().isEmpty()){
                            Toast.makeText(
                                    LoginActivity.this,
                                    "Oops! your name is empty. May I know your name?",
                                    Toast.LENGTH_LONG
                            ).show();
                            return;
                        }

                        // Create an explicit intent to navigate from this activity to MainActivity
                        Intent navigationIntent = new Intent(
                                LoginActivity.this,
                                MainActivity.class
                        );

                        //Set the value in the intent
                        navigationIntent.putExtra("PROFILE_NAME",name);

                        // Launch the activity
                        startActivity(navigationIntent);
                    }
                }
        );
    }


}