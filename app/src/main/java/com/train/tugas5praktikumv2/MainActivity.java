package com.train.tugas5praktikumv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText etHome,etAway;
    private ImageView ivHome,ivAway;
    private Button btnTeam;
    private String homeTeam, awayTeam;
    private Uri homeLogo, awayLogo;

    private static final int HOME_REQUEST_CODE = 1;
    private static final int AWAY_REQUEST_CODE = 2;
    private static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etHome = findViewById(R.id.home_team);
        etAway = findViewById(R.id.away_team);
        ivHome = findViewById(R.id.home_logo);
        ivAway = findViewById(R.id.away_logo);
        btnTeam = findViewById(R.id.btn_team);


        btnTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeTeam = etHome.getText().toString();
                awayTeam = etAway.getText().toString();

                if (TextUtils.isEmpty(homeTeam)) {
                    etHome.setError("Insert The Home Team Name!");
                    return;
                } else if (TextUtils.isEmpty(awayTeam)){
                   etHome.setError("Insert The Away Team Name!");
                    return;
                } else {
                    Intent intent = new Intent(MainActivity.this, MatchActivity.class);
                    intent.putExtra("homeName", homeTeam);
                    intent.putExtra("awayName", awayTeam);
                    intent.putExtra("homeLogo", homeLogo.toString());
                    intent.putExtra("awayLogo", awayLogo.toString());
                    startActivity(intent);
                }
            }
        });


        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), HOME_REQUEST_CODE);
            }
        });


        ivAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), AWAY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            Log.d(TAG, "Cancelled");
            return;
        }
        else if(requestCode == HOME_REQUEST_CODE){
            if(data != null){
                try {
                    Uri imageUri = data.getData();
                    homeLogo = imageUri;
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    ivHome.setImageBitmap(bitmap);
                }
                catch (IOException error){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, error.getMessage());
                }
            }
        }
        else if(requestCode == AWAY_REQUEST_CODE){
            if(data != null){
                try {
                    Uri imageUri = data.getData();
                    awayLogo = imageUri;
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    ivAway.setImageBitmap(bitmap);
                }
                catch (IOException error){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, error.getMessage());
                }
            }
        }
    }

}