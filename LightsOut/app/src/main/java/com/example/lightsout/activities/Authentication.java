package com.example.lightsout.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lightsout.ApplicationController;
import com.example.lightsout.R;

public class Authentication extends AppCompatActivity {

    private Button loginButton;

    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        loginButton = findViewById(R.id.loginButton);
        name = findViewById(R.id.authenticationEditText);

        Intent startMenu = new Intent(this,Menu.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty())
                {
                    Toast.makeText(ApplicationController.getInstance(),"Name can't remain empty.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    startMenu.putExtra("playerName",name.getText().toString());
                    startActivity(startMenu);
                  //  name.setText("");
                    finish();
                }
            }
        });
    }
}