package com.train.tugas5praktikumv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {

    private int homeScore, awayScore;
    private TextView tvScrHome, tvScrAway, tvHome, tvAway;
    private ImageView homeLogo, awayLogo;
    private Button add1Home, add2Home, add3Home, add1Away, add2Away, add3Away, btnResult, btnReset;
    private String homeTeam, awayTeam, winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_match);


        tvScrHome = findViewById(R.id.score_home);
        tvScrAway = findViewById(R.id.score_away);
        tvHome = findViewById(R.id.txt_home);
        tvAway = findViewById(R.id.txt_away);
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);
        add1Home = findViewById(R.id.btn_add_home);
        add2Home = findViewById(R.id.btn_add_home2);
        add3Home = findViewById(R.id.btn_add_home3);
        add1Away = findViewById(R.id.btn_add_away);
        add2Away = findViewById(R.id.btn_add_away2);
        add3Away = findViewById(R.id.btn_add_away3);
        btnResult = findViewById(R.id.btn_result);
        btnReset = findViewById(R.id.btn_reset);
        homeScore = 0;
        awayScore = 0;
        tvScrHome.setText(String.valueOf(homeScore));
        tvScrAway.setText(String.valueOf(awayScore));


        Bundle bundle = getIntent().getExtras();
        homeTeam = bundle.getString("homeName");
        tvHome.setText(homeTeam);
        awayTeam = bundle.getString("awayName");
        tvAway.setText(awayTeam);
        homeLogo.setImageURI(Uri.parse(bundle.getString("homeLogo")));
        awayLogo.setImageURI(Uri.parse(bundle.getString("awayLogo")));


        add1Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeScore += 1;
                tvScrHome.setText(String.valueOf(homeScore));
            }
        });

        add2Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeScore += 2;
                tvScrHome.setText(String.valueOf(homeScore));
            }
        });

        add3Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeScore += 3;
                tvScrHome.setText(String.valueOf(homeScore));
            }
        });

        add1Away.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                awayScore += 1;
                tvScrAway.setText(String.valueOf(awayScore));
            }
        });

        add2Away.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                awayScore += 2;
                tvScrAway.setText(String.valueOf(awayScore));
            }
        });

        add3Away.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                awayScore += 3;
                tvScrAway.setText(String.valueOf(awayScore));
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                winner = "empty";
                if(homeScore > awayScore){
                    winner = homeTeam + " is The Winner!";
                }
                else if (homeScore == awayScore){
                    winner = "It's a Draw!";
                }
                else {
                    winner = awayTeam + " is The Winner!";
                }

                Intent result = new Intent(MatchActivity.this, ResultActivity.class);
                result.putExtra("champ", winner);
                startActivity(result);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeScore = 0;
                awayScore = 0;
                tvScrHome.setText(String.valueOf(homeScore));
                tvScrAway.setText(String.valueOf(awayScore));
            }
        });



    }
}