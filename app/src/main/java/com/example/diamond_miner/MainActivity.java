package com.example.diamond_miner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private final int MAX = 4, MIN = 0, ROWS = 7, COLS = 5;
    private int life = 2;
    private boolean over = false;
    private boolean isGame = true;
    private int speed;
    private ImageView[][] stones;
    private ImageView[] miners, lives;
    private ImageView diamond;
    private MediaPlayer mp;
    private ImageButton rightbtn, leftbtn;
    private SensorManager sensorManager;
    private Sensor sensor;
    private int gameType, gameSpeed;
    private TextView txt_score;
    private String score;
    public int scorecounter = 0;
    private SensorEventListener sensorEventListener;
    private String playerName;
    private ArrayList<player> players;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_score = findViewById(R.id.score);
        leftbtn = findViewById(R.id.main_BTN_left);
        rightbtn = findViewById(R.id.main_BTN_right);
        //   name = getIntent().getStringExtra("Name_key");

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                if (Math.abs(x) > Math.abs(y)) {
                    if (x < 0) {
                        right();
                    }
                    if (x > 0) {
                        left();
                    }
                } else {
                    if (y < 0) {
                        //speedUP
                        speed = 200;
                    }
                    if (y > 0) {
                        //speedDOWN
                        speed = 700;
                    }
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        initImages();
        invisibleStones();
        if (isGame) {
            mainLoop();
        }
        manageGameSpeed();

    }

    private void initImages() {
        stones = new ImageView[][]{{
                findViewById(R.id.main_IMG_a1),
                findViewById(R.id.main_IMG_a2),
                findViewById(R.id.main_IMG_a3),
                findViewById(R.id.main_IMG_a4),
                findViewById(R.id.main_IMG_a5)

        }, {
                findViewById(R.id.main_IMG_a6),
                findViewById(R.id.main_IMG_a7),
                findViewById(R.id.main_IMG_a8),
                findViewById(R.id.main_IMG_a9),
                findViewById(R.id.main_IMG_a10)
        },
                {
                        findViewById(R.id.main_IMG_a11),
                        findViewById(R.id.main_IMG_a12),
                        findViewById(R.id.main_IMG_a13),
                        findViewById(R.id.main_IMG_a14),
                        findViewById(R.id.main_IMG_a15)
                }
                , {
                findViewById(R.id.main_IMG_a16),
                findViewById(R.id.main_IMG_a17),
                findViewById(R.id.main_IMG_a18),
                findViewById(R.id.main_IMG_a19),
                findViewById(R.id.main_IMG_a20)},
                {
                        findViewById(R.id.main_IMG_a21),
                        findViewById(R.id.main_IMG_a22),
                        findViewById(R.id.main_IMG_a23),
                        findViewById(R.id.main_IMG_a24),
                        findViewById(R.id.main_IMG_a25)

                },
                {findViewById(R.id.main_IMG_a26),
                        findViewById(R.id.main_IMG_a27),
                        findViewById(R.id.main_IMG_a28),
                        findViewById(R.id.main_IMG_a29),
                        findViewById(R.id.main_IMG_a30)},
                {
                        findViewById(R.id.main_IMG_a31),
                        findViewById(R.id.main_IMG_a32),
                        findViewById(R.id.main_IMG_a33),
                        findViewById(R.id.main_IMG_a34),
                        findViewById(R.id.main_IMG_a35)
                }};

        miners = new ImageView[]{
                findViewById(R.id.main_IMG_a36),
                findViewById(R.id.main_IMG_a37),
                findViewById(R.id.main_IMG_a38),
                findViewById(R.id.main_IMG_a39),
                findViewById(R.id.main_IMG_a40)
        };

        miners[0].setVisibility(View.VISIBLE);
        miners[1].setVisibility(View.INVISIBLE);
        miners[2].setVisibility(View.INVISIBLE);
        miners[3].setVisibility(View.INVISIBLE);
        miners[4].setVisibility(View.INVISIBLE);

        lives = new ImageView[]{
                findViewById(R.id.life1),
                findViewById(R.id.life2),
                findViewById(R.id.life3)};

        diamond = findViewById(R.id.main_IMG_diamond);
        diamond.setVisibility(View.INVISIBLE);


    }

    @Override
    protected void onResume() {

        isGame = true;
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        isGame = false;

    }

    @Override
    protected void onPause() {
        isGame = false;
        super.onPause();


    }

    private void manageGameSpeed() {
        Bundle bundle = getIntent().getExtras();
        gameType = bundle.getInt("gameSpeed");
        if (bundle != null) {
            if (gameType == 0) {
                speed = 500;
                gameSpeed = Constants.SLOW;
                sensorManager.unregisterListener(sensorEventListener);
                buttonMode();
            } else if (gameType == 1) {
                gameSpeed = Constants.FAST;
                sensorManager.unregisterListener(sensorEventListener);
                speed = 200;
                buttonMode();
            } else if (gameType == Constants.SENSOR) {
                speed = 400;
                sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
                rightbtn.setVisibility(View.INVISIBLE);
                leftbtn.setVisibility(View.INVISIBLE);

            }
        }
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

    public void checkCollision(int col) {
        diamond.setVisibility(View.INVISIBLE);

        if (miners[col].getVisibility() == View.VISIBLE) {

            if (col == 1 || col == 4) {
                scorecounter += 10;
                diamond.setVisibility(View.VISIBLE);
                signal.diamondAnimate(diamond);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.bounce);
                mp.start();

            } else {
                signal.vibrate(this, 100);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.boing);
                mp.start();
                if (checkLives() && life >= 0) {
                    if (life > 0) {
                        Toast.makeText(this, "Oh , be careful!", Toast.LENGTH_SHORT).show();
                    }
                    scorecounter -= 5;
                    lives[life].setVisibility(View.INVISIBLE);
                    life--;
                }
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


    private void buttonMode() {
        leftbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                left();

            }

        });

        rightbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                right();

            }

        });


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


    public void left() {

        if (miners[1].getVisibility() == View.VISIBLE) {
            miners[1].setVisibility(View.INVISIBLE);
            miners[0].setVisibility(View.VISIBLE);

        } else if (miners[2].getVisibility() == View.VISIBLE) {
            miners[2].setVisibility(View.INVISIBLE);
            miners[1].setVisibility(View.VISIBLE);

        } else if (miners[3].getVisibility() == View.VISIBLE) {
            miners[3].setVisibility(View.INVISIBLE);
            miners[2].setVisibility(View.VISIBLE);

        } else if (miners[4].getVisibility() == View.VISIBLE) {
            miners[4].setVisibility(View.INVISIBLE);
            miners[3].setVisibility(View.VISIBLE);

        }

    }

    public void right() {
        if (miners[0].getVisibility() == View.VISIBLE) {
            miners[0].setVisibility(View.INVISIBLE);
            miners[1].setVisibility(View.VISIBLE);
        } else if (miners[1].getVisibility() == View.VISIBLE) {
            miners[1].setVisibility(View.INVISIBLE);
            miners[2].setVisibility(View.VISIBLE);

        } else if (miners[2].getVisibility() == View.VISIBLE) {
            miners[2].setVisibility(View.INVISIBLE);
            miners[3].setVisibility(View.VISIBLE);

        } else if (miners[3].getVisibility() == View.VISIBLE) {
            miners[3].setVisibility(View.INVISIBLE);
            miners[4].setVisibility(View.VISIBLE);

        }

    }

    private void Score() {
        score = String.format("%d", scorecounter++);
        txt_score.setText(score);
        txt_score.setTextColor(Color.rgb(255, 255, 255));

    }

    public player setPlayer() {

        playerName = getIntent().getStringExtra("Name_key");
        player pl = new player();
        pl.setName(playerName);
        pl.setScore(score);
        Gson gson = new Gson();
        String jsn = gson.toJson(pl);
        Log.d("pttt", "Json=" + jsn);
        return pl;
    }


    public void goToGameOverActivity() {
        Intent intent = new Intent(MainActivity.this, GameOver.class);
        intent.putExtra("score_key", score);
        setPlayer();
        intent.putExtra("Name_key", playerName);
        startActivity(intent);
    }
}

