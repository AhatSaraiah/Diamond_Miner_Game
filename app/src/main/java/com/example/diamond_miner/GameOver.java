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
    private double latitude;
    private double longitude;
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

        Bundle b = getIntent().getExtras();
        if (b != null) {
            score = b.getInt(Constants.SCORE_KEY);
            latitude = b.getDouble(Constants.LATITUDE_KEY, latitude);
            longitude = b.getDouble(Constants.LONGITUDE_KEY, longitude);
        }

        playerManager = new PlayerManager(GameOver.this);
    }


    public void clickOnHighScoreBtn() {
        Intent intent = new Intent(GameOver.this, Map.class);

        DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
        String date = df.format(Calendar.getInstance().getTime());
        score = Integer.parseInt(totalScore);

        if (newHighScore) {
            player p = new player(playerName, score, date, latitude, longitude);
            playerManager.addPlayer(p);
        }
        GameOver.this.startActivity(intent);
        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (score > playerManager.getLastPlace() || playerManager.getRecords().size() < Constants.ARRAY_MAX_SIZE) {
            txt_notHighScore.setVisibility(View.INVISIBLE);
            newHighScore = true;
        } else {
            txt_notHighScore.setVisibility(View.VISIBLE);
            newHighScore = false;
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
