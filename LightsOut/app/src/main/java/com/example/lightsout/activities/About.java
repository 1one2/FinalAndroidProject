package com.example.lightsout.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lightsout.R;
import com.example.lightsout.fragments.AboutAppFragment;
import com.example.lightsout.fragments.AboutCreatorsFragment;

public class About extends AppCompatActivity {

    private TextView aboutTheApp;
    private TextView aboutTheCreators;

    private ImageView back;

    private TextView displayPlayerName;
    private String playerName;

    private AboutAppFragment appFragment = new AboutAppFragment();
    private AboutCreatorsFragment creatorsFragment = new AboutCreatorsFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initializeViews();
    }

    void initializeViews()
    {
        aboutTheApp = findViewById(R.id.aboutThisApp);
        aboutTheCreators = findViewById(R.id.aboutTheCreators);
        displayPlayerName = findViewById(R.id.aboutPlayerName);

        back = findViewById(R.id.aboutMenuBackButton);

        playerName = getIntent().getExtras().getString("playerName");
        displayPlayerName.setText(displayPlayerName.getText().toString() + " " + playerName);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        aboutTheApp.setBackgroundColor(R.color.blue);
        aboutTheApp.setTextColor(getResources().getColor(R.color.lightGray));
        getSupportFragmentManager().beginTransaction()

                .replace(R.id.aboutFragment,appFragment).addToBackStack(null).commit();

        aboutTheApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aboutTheApp.setBackgroundColor(R.color.blue);
                aboutTheApp.setTextColor(getResources().getColor(R.color.lightGray));
                getSupportFragmentManager().beginTransaction()

                        .replace(R.id.aboutFragment,appFragment).addToBackStack(null).commit();

                aboutTheCreators.setBackgroundColor(getResources().getColor(R.color.lightGray));
                aboutTheCreators.setTextColor(getResources().getColor(R.color.blue));

            }
        });

        aboutTheCreators.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutTheCreators.setBackgroundColor(R.color.blue);
                aboutTheCreators.setTextColor(getResources().getColor(R.color.lightGray));
                getSupportFragmentManager().beginTransaction()

                        .replace(R.id.aboutFragment,creatorsFragment).addToBackStack(null).commit();

                aboutTheApp.setBackgroundColor(getResources().getColor(R.color.lightGray));
                aboutTheApp.setTextColor(getResources().getColor(R.color.blue));

            }
        });
    }
}