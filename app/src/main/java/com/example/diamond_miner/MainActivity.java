package com.example.diamond_miner;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private final int MAX = 2;
    private final int MIN = 0;
    private final int ROWS = 7;
    private final int COLS = 3;
    private static int life = 2;
    private boolean over = false;
    private boolean isGame = true;
    private int speed = 400;
    public static int counter = 0;
    private TextView score;
    private ImageView[][] stones;
    private ImageView[] miners;
    private ImageView[] lives;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = findViewById(R.id.score);
        stones = new ImageView[][]{{
                findViewById(R.id.main_IMG_a1),
                findViewById(R.id.main_IMG_a2),
                findViewById(R.id.main_IMG_a3)
        }, {
                findViewById(R.id.main_IMG_a4),
                findViewById(R.id.main_IMG_a5),
                findViewById(R.id.main_IMG_a6)},
                {
                findViewById(R.id.main_IMG_a7),
                findViewById(R.id.main_IMG_a8),
                findViewById(R.id.main_IMG_a9)}
                , {
                findViewById(R.id.main_IMG_a10),
                findViewById(R.id.main_IMG_a11),
                findViewById(R.id.main_IMG_a12)},
                {
                findViewById(R.id.main_IMG_a13),
                findViewById(R.id.main_IMG_a14),
                findViewById(R.id.main_IMG_a15)},
                {
                findViewById(R.id.main_IMG_a16),
                findViewById(R.id.main_IMG_a17),
                findViewById(R.id.main_IMG_a18)},
                {
                findViewById(R.id.main_IMG_a19),
                findViewById(R.id.main_IMG_a20),
                findViewById(R.id.main_IMG_a21)
                }};


        miners = new ImageView[]{
                findViewById(R.id.main_IMG_a22),
                findViewById(R.id.main_IMG_a23),
                findViewById(R.id.main_IMG_a24)};

        miners[0].setVisibility(View.VISIBLE);
        miners[1].setVisibility(View.INVISIBLE);
        miners[2].setVisibility(View.INVISIBLE);

        lives = new ImageView[]
                {findViewById(R.id.life1),
                 findViewById(R.id.life2)
                ,findViewById(R.id.life3)};

        ImageButton leftbtn = findViewById(R.id.main_BTN_left);
        ImageButton rightbtn = findViewById(R.id.main_BTN_right);

        leftbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (miners[1].getVisibility() == View.VISIBLE) {
                    miners[1].setVisibility(View.INVISIBLE);
                    miners[0].setVisibility(View.VISIBLE);

                }
                if (miners[2].getVisibility() == View.VISIBLE) {
                    miners[2].setVisibility(View.INVISIBLE);
                    miners[1].setVisibility(View.VISIBLE);

                }

            }

        });

        rightbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (miners[1].getVisibility() == View.VISIBLE) {
                    miners[1].setVisibility(View.INVISIBLE);
                    miners[2].setVisibility(View.VISIBLE);

                }
                if (miners[0].getVisibility() == View.VISIBLE) {
                    miners[0].setVisibility(View.INVISIBLE);
                    miners[1].setVisibility(View.VISIBLE);

                }

            }

        });
        invisibleStones();
        mainLoop();

    }

    @Override
    protected void onResume() {
        isGame = true;
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        counter = 0;

    }

    @Override
    protected void onPostResume() {
        isGame = true;
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        isGame = false;
        super.onPause();
    }

    private void invisibleStones() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                stones[i][j].setVisibility(View.INVISIBLE);
            }
        }
    }

    private void changePosition(int row, int col) {
        if (row != 0)
            stones[row - 1][col].setVisibility(View.INVISIBLE);
        if (row < ROWS)
            stones[row][col].setVisibility(View.VISIBLE);
        if (row == ROWS) {
            checkCollision(col);
        }
    }

    private void checkCollision(int col) {
        if (miners[col].getVisibility() == View.VISIBLE) {
            signal.vibrate(this, 100);
            mp = MediaPlayer.create(getApplicationContext(), R.raw.boing);
            mp.start();
            if (checkLives() && life >= 0) {
                if (life > 0) {
                    Toast.makeText(this, "Oh , be careful!", Toast.LENGTH_SHORT).show();
                }
                counter -= 5;
                lives[life].setVisibility(View.INVISIBLE);
                life--;
            }
        }
        if (!checkLives()) {
            signal.vibrate(this, 500);
            Toast.makeText(this, "Hard luck!", Toast.LENGTH_SHORT).show();
            over = true;


            life = 2;
        }

    }

    private boolean checkLives() {
        for (int i = 0; i < lives.length; i++) {
            if (lives[i].getVisibility() == View.VISIBLE)
                return true;
        }
        return false;

    }

    private void mainLoop() {
        final Handler handler = new Handler();
        Runnable myRun = new Runnable() {
            @Override
            public void run() {

                if (!over) {
                    Score();
                    /// random column to show
                    int showStones = (int) ((Math.random() * ((MAX - MIN) + 1)) + MIN);
                    secondLoop(showStones, 0);
                    mainLoop();
                } else {
                    goToGameOverActivity();
                    finish();
                }

            }
        };
        handler.postDelayed(myRun, 1000);
    }

    private void secondLoop(final int col, final int row) {
        final Handler handler = new Handler();
        Runnable myRun = new Runnable() {
            @Override
            public void run() {
                if (row <= ROWS && !over && isGame) {
                    secondLoop(col, row + 1);
                    changePosition(row, col);

                }

            }
        };
        handler.postDelayed(myRun, speed);
    }

    private void Score() {
        counter++;
        score.setText("Score: " + counter);
        score.setTextColor(Color.rgb(255, 255, 255));
    }

    public void goToGameOverActivity() {
        Intent intent = new Intent(MainActivity.this, GameOver.class);
        startActivity(intent);
    }


}



