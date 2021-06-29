package com.example.lightsout.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lightsout.ApplicationController;
import com.example.lightsout.R;
import com.example.lightsout.adapters.RankingEntryAdapter;
import com.example.lightsout.data.RankingEntryRepository;
import com.example.lightsout.fragments.RankingFragment;
import com.example.lightsout.models.RankingEntries;

public class Ranking extends AppCompatActivity {

    //region DECLARATIONS

    private RankingEntryRepository repository = new RankingEntryRepository();

    private ImageView back;
    private ImageView refresh;

    private RankingEntries entries = new RankingEntries();

    private RankingFragment rankingFragment = new RankingFragment();

    private RankingEntryAdapter adapter;

    private TextView displayPlayerName;
    private String playerName;


    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        viewsInitialization();
    }

    void viewsInitialization() {

        displayPlayerName = findViewById(R.id.rankingPlayerName);
        playerName = getIntent().getExtras().getString("playerName");

        displayPlayerName.setText(displayPlayerName.getText().toString() + " " + playerName);

        back = findViewById(R.id.rankingMenuBackButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        refresh = findViewById(R.id.rankingMenuRefresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
            }
        });


        entries.initEntries();

        adapter = new RankingEntryAdapter(entries.allRankingEntries);

        adapter.notifyDataSetChanged();

        getSupportFragmentManager().beginTransaction()

                .replace(R.id.rankingFragment, rankingFragment).addToBackStack(null).commit();


    }

    public RankingEntryAdapter getAdapter()
    {
        return adapter;
    }
}