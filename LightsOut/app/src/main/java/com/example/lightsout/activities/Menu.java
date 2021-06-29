package com.example.lightsout.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lightsout.ApplicationController;
import com.example.lightsout.R;
import com.example.lightsout.interfaces.OnItemClickListener;
import com.example.lightsout.workers.ShowWorker;

public class Menu extends AppCompatActivity {

    //region DECLARATIONS

    //region BUTTONS

    private Button playButton;
    private Button rankingButton;
    private Button aboutButton;
    private Button exitButton;

    //endregion

    //region Intents

    private Intent playIntent;
    private Intent rankingIntent;
    private Intent aboutIntent;

    //endregion

    private OnItemClickListener clickListener;

    private String playerName;

    private TextView displayPlayerName;

    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        viewsInitialization();
    }

    void viewsInitialization()
    {
        //region BUTTONS
        playButton = findViewById(R.id.menuPlay);
        rankingButton = findViewById(R.id.menuRanking);
        aboutButton = findViewById(R.id.menuAbout);
        exitButton = findViewById(R.id.menuExit);
        //endregion

        playerName = getIntent().getExtras().getString("playerName");

        displayPlayerName = findViewById(R.id.menuPlayerName);
        displayPlayerName.setText(displayPlayerName.getText().toString() + " " +  playerName);

        //region INTENTS

        playIntent = new Intent(this,Game.class);
        playIntent.putExtra("playerName",playerName);

        rankingIntent = new Intent(this,Ranking.class);
        rankingIntent.putExtra("playerName",playerName);

        aboutIntent = new Intent(this,About.class);
        aboutIntent.putExtra("playerName",playerName);

        //endregion

        //region Click-Listeners

        clickListener = new OnItemClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.menuPlay)
                    startActivity(playIntent);
                else if(view.getId() == R.id.menuRanking)
                    startActivity(rankingIntent);
                else if(view.getId() == R.id.menuAbout)
                    startActivity(aboutIntent);
                else if(view.getId() == R.id.menuExit)
                {
                    WorkRequest request =
                            new OneTimeWorkRequest.Builder(ShowWorker.class).build();
                    WorkManager.getInstance(ApplicationController.getInstance()).enqueue(request);
                    finish();
                }
            }
        };

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(playButton);
            }
        });

        rankingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(rankingButton);
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(aboutButton);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(exitButton);
            }
        });

        //endregion
    }
}