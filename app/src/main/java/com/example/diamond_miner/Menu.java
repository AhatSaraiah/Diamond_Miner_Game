package com.example.diamond_miner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class Menu extends AppCompatActivity {
    private Button slow_btn;
    private Button fast_btn;
    private Button sensor_btn;
    private TextView gameName;
    private TextView enterName;
    private EditText name;
    private String playerName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        gameName = findViewById(R.id.menu_text);
        enterName = findViewById(R.id.name);
        name = findViewById(R.id.inputName);
        gameName.setText("Diamond Miner");
        enterName.setText("Enter your name and choose the game mode");

        initButtons();

    }

    public void initButtons() {

        slow_btn = findViewById(R.id.slow_btn);
        fast_btn = findViewById(R.id.fast_btn);
        sensor_btn = findViewById(R.id.sensor_btn);

        slow_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("gameSpeed", Constants.SLOW);
                goToGameActivity(bundle);

            }
        });
        fast_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("gameSpeed", Constants.FAST);
                goToGameActivity(bundle);
            }
        });
        sensor_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("gameSpeed", Constants.SENSOR);
                goToGameActivity(bundle);

            }
        });

    }

    public void goToGameActivity(Bundle bundle) {
        Intent startGame = new Intent(Menu.this, MainActivity.class);
        playerName = name.getText()+"";
        startGame.putExtra("Name_key", playerName);
        startGame.putExtras(bundle);
        startActivity(startGame);
    }

}