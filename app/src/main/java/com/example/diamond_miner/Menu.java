package com.example.diamond_miner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends AppCompatActivity {
    private Button newGamebtn;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
         mTextView = findViewById(R.id.menu_text);
        mTextView.setText("Diamond Miner");
        mTextView.setTextColor(Color.WHITE);



        newGamebtn = findViewById(R.id.button);
       newGamebtn.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v){
                goToGameActivity();

            }
        });


    }


    public void goToGameActivity() {
        Intent intent = new Intent(Menu.this, MainActivity.class);
        startActivity(intent);
    }

}
