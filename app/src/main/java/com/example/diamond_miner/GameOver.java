package com.example.diamond_miner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class GameOver extends AppCompatActivity {
    private Button menu, button_highScoreLayout;
    private ImageView gameOver;
    private String totalScore;
    private int score;
    private TextView txt_totalScore;
    private TextView txt_name;
    private String playerName;
    private PlayerManager playerManager;
    private TextView txt_notHighScore;
    private boolean newHighScore = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        menu = findViewById(R.id.menu_BTN);
        gameOver = findViewById(R.id.gameOver);
        signal.gameOverAnimate(gameOver);
        setTotalScore();
        txt_notHighScore = findViewById(R.id.txt_notHighScore);
        button_highScoreLayout = findViewById(R.id.button_highScoreLayout);

        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToMenuActivity();
                finish();
            }
        });

        button_highScoreLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnHighScoreBtn();
            }
        });
    }


    public void clickOnHighScoreBtn() {
        DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
        String date = df.format(Calendar.getInstance().getTime());
        if (newHighScore) {
            player p = new player(playerName, score, date);
            playerManager.addUser(p);
        }


    }


    public void setTotalScore() {
        txt_totalScore = findViewById(R.id.txt_totalScore);
        txt_name = findViewById(R.id.txt_name);
        totalScore = getIntent().getStringExtra("score_key");
        playerName = getIntent().getStringExtra("Name_key");
        txt_name.setText("Player name: " + playerName);
        txt_totalScore.setText("Total score: " + totalScore);
        score = Integer.parseInt(totalScore);

    }

    public void goToMenuActivity() {
        Intent intent = new Intent(GameOver.this, Menu.class);
        startActivity(intent);
    }
}
