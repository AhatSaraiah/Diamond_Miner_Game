package com.example.diamond_miner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class GameOver  extends AppCompatActivity {
    private Button menu;
    private ImageView gameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        menu = findViewById(R.id.menu_BTN);
        gameOver=findViewById(R.id.gameOver);
        animateItCode();

        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                goToMenuActivity();
                finish();
            }
        });

    }

    private void animateItCode() {
        gameOver.setScaleX(0);
       gameOver.setScaleY(0);
        gameOver.setRotation(0);
        gameOver.animate()
                .rotation(360)
                .scaleX(1)
                .scaleY(1)
                .setDuration(2000)
                .setInterpolator(new BounceInterpolator())
                .start();
    }

    public void goToMenuActivity() {
        Intent intent = new Intent(GameOver.this, Menu.class);
        startActivity(intent);
    }
}
