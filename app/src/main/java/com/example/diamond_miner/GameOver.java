package com.example.diamond_miner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class GameOver extends AppCompatActivity {
    private Button menu;
    private ImageView gameOver;
    private String totalScore;
    private TextView txt_totalScore;
    private TextView txt_name;
    private String playerName;
   // private topScores topScores;
    private player currentPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        menu = findViewById(R.id.menu_BTN);
        gameOver = findViewById(R.id.gameOver);
        signal.gameOverAnimate(gameOver);
        setTotalScore();

         //  topScores = new topScores();
        //   topScores.setActivity(this);
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//           transaction.replace(R.id.main_LAY, topScores);
//          transaction.commit();

        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToMenuActivity();
                finish();
            }
        });


    }

    public void setTotalScore() {
        txt_totalScore = findViewById(R.id.txt_totalScore);
        txt_name = findViewById(R.id.txt_name);
        totalScore = getIntent().getStringExtra("score_key");
        playerName = getIntent().getStringExtra("Name_key");
        txt_name.setText("Player name: " + playerName);
        txt_totalScore.setText("Total score: " + totalScore);

    }

    public void goToMenuActivity() {
        Intent intent = new Intent(GameOver.this, Menu.class);
        //   Intent intent = new Intent(GameOver.this, locationActivity.class);
        startActivity(intent);
    }
}
