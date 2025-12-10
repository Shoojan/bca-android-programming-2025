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

public class HimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_him);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText giftEditText = findViewById(R.id.giftEditText);
        Button sendGiftButton = findViewById(R.id.sendGiftButton);

        sendGiftButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String gift = giftEditText.getText().toString();
                        if(gift.isEmpty()){
                            Toast.makeText(HimActivity.this,
                                    "Please provide a gift. Otherwise she will be angry.",
                                    Toast.LENGTH_SHORT
                            ).show();
                            return;
                        }
                        Intent giftIntent = new Intent(HimActivity.this. <baaki-xa> );
                        giftIntent.putExtra("GIFT",gift);
                        startActivityForResult(giftIntent, 1);
                    }
                }
        );

    }
}