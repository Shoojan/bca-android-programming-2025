package com.example.unit_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HimActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 1;


    /**
     * Activity Result Launcher
     */
    ActivityResultLauncher<Intent> activityResultLauncher= registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getData() == null) return;
                    if(o.getResultCode() != RESULT_OK) return;

                    String giftResponse = o.getData().getStringExtra(Constant.GIFT_RESPONSE);

                    Toast.makeText(HimActivity.this, giftResponse, Toast.LENGTH_SHORT).show();

                }
            }
    );

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
                        Intent giftIntent = new Intent(HimActivity.this, HerActivity.class );
                        giftIntent.putExtra(Constant.GIFT,gift);
                        startActivityForResult(giftIntent, REQUEST_CODE);

//                        activityResultLauncher.launch(giftIntent);
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null) return;
        if(resultCode != RESULT_OK) return;
        if(requestCode == REQUEST_CODE){
            String giftResponse = data.getStringExtra(Constant.GIFT_RESPONSE);

            Toast.makeText(HimActivity.this, giftResponse, Toast.LENGTH_SHORT).show();
        }

    }
}