package com.example.unit_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_her);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Receive the gift
        Intent giftIntent = getIntent();
        String gift = giftIntent.getStringExtra(Constant.GIFT);

        // Get the object reference
        TextView herTextView = findViewById(R.id.herTitle);
        herTextView.setText("Gift received: "+gift);

        EditText giftResponseEditText = findViewById(R.id.giftResponseEditText);
        Button sendGiftResponseButton = findViewById(R.id.sendGiftResponseButton);

        //Register onClick event in the button
        sendGiftResponseButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Grab the value from EditText
                        String giftResponse = giftResponseEditText.getText().toString();
                        if(giftResponse.isEmpty()){
                            return;
                        }

                        Intent responseIntent = new Intent();
                        responseIntent.putExtra(Constant.GIFT_RESPONSE, giftResponse);

                        setResult(RESULT_OK, responseIntent);
                        finish(); //navigate back to parent activity
                    }
                }
        );

    }
}