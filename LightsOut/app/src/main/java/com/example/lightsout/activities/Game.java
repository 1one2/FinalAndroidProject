package com.example.lightsout.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lightsout.ApplicationController;
import com.example.lightsout.R;
import com.example.lightsout.adapters.LightAdapter;
import com.example.lightsout.data.RankingEntryRepository;
import com.example.lightsout.fragments.GameFragment;
import com.example.lightsout.interfaces.LightClickListener;
import com.example.lightsout.models.Board;
import com.example.lightsout.models.Light;
import com.example.lightsout.models.RankingEntry;

public class Game extends AppCompatActivity {

    //region Declarations

    //region Buttons

    private ImageView pause;
    private ImageView restart;
    private ImageView submit;
    private ImageView back;
    private ImageView resume;

    //endregion

    private RecyclerView recyclerView;

    //region Menus

    private FrameLayout pausedMenu;

    //endregion

    private Chronometer timer;
    private Chronometer timerPausedMenu = new Chronometer(ApplicationController.getInstance());

    private TextView displayPlayerName;
    private String playerName;

    private Board board = new Board();

    private long elapsedSeconds;
    private long timeSpendPaused;

    private GameFragment gameFragment;

    private boolean isGamePaused = false;
    private boolean isFirstMove = true;
    private boolean alreadySubmitted = false;

    RankingEntryRepository repository = new RankingEntryRepository();

    LightAdapter adapter = new LightAdapter(board, new LightClickListener() {
        @Override
        public void onLightClicked(Light light) {

            if(!board.isGameDone() && !isGamePaused)
            {
                if(isFirstMove)
                {
                    timer.setBase(SystemClock.elapsedRealtime());
                    timer.start();
                    isFirstMove = false;
                }
                board.switchLights(board.getLights().indexOf(light));

                if(board.isGameDone())
                {
                    timer.stop();

                    elapsedSeconds = (SystemClock.elapsedRealtime() - timer.getBase());
                }

                adapter.notifyDataSetChanged();
            }

        }
    });

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        viewsInitialization();
    }

    public LightAdapter getAdapter()
    {
        return adapter;
    }

    void viewsInitialization()
    {
        //region Buttons

        pause = findViewById(R.id.gamePauseButton);
        submit = findViewById(R.id.gameSubmitButton);
        restart = findViewById(R.id.gameRestartButton);
        resume = findViewById(R.id.gamePausedResumeButton);
        back = findViewById(R.id.gameBackButton);
        //endregion

        timer = findViewById(R.id.gameTimer);

        pausedMenu = findViewById(R.id.gamePausedMenu);


        board.initBoard();

        playerName = getIntent().getExtras().getString("playerName");
        displayPlayerName = findViewById(R.id.gamePlayerName);
        displayPlayerName.setText(displayPlayerName.getText().toString() + " " + playerName);

        gameFragment = new GameFragment();

        getSupportFragmentManager().beginTransaction()

                .replace(R.id.gameFragment, gameFragment).addToBackStack(null).commit();

        //region Click Listeners

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(board.isGameDone() && !alreadySubmitted);
                    repository.insertNewRankingEntry(
                            new RankingEntry(playerName, elapsedSeconds),
                            new RankingEntryRepository.InsertListener() {
                                @Override
                                public void onSuccess() {
                                    Toast.makeText(ApplicationController.getInstance(),"Time submitted.",Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
            }
        });



        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isGamePaused)
                {
                    pausedMenu.setVisibility(View.VISIBLE);
                    pausedMenu.bringToFront();

                    isGamePaused = true;

                    timer.stop();
                    timerPausedMenu.setBase(SystemClock.elapsedRealtime());
                    timerPausedMenu.start();
                }

            }
        });

        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pausedMenu.setVisibility(View.INVISIBLE);

                isGamePaused = false;

                timerPausedMenu.stop();
                timeSpendPaused = (SystemClock.elapsedRealtime() - timerPausedMenu.getBase());

                timer.setBase(timer.getBase() + timeSpendPaused);
                timer.start();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isGamePaused)
                {
                    timer.stop();

                    board.initBoard();
                    adapter.notifyDataSetChanged();

                    isFirstMove = true;
                    alreadySubmitted = false;
                }
            }
        });

        //endregion
    }
}