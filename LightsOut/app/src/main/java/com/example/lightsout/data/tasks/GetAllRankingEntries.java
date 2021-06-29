package com.example.lightsout.data.tasks;

import android.os.AsyncTask;

import com.example.lightsout.data.RankingDatabase;
import com.example.lightsout.data.RankingEntryRepository;
import com.example.lightsout.models.RankingEntry;

import java.util.List;

public class GetAllRankingEntries extends AsyncTask<Void,Void, List<RankingEntry>> {


    private RankingDatabase database;
    private RankingEntryRepository.QueryListener listener;


    public GetAllRankingEntries(RankingDatabase pDatabase, RankingEntryRepository.QueryListener pListener)
    {
        database = pDatabase;
        listener = pListener;
    }
    @Override
    protected List<RankingEntry> doInBackground(Void... voids) {
        return database.dao().getAllRankingEntries();
    }

    @Override
    protected void onPostExecute(List<RankingEntry> allEntries)
    {
        super.onPostExecute(allEntries);
        listener.onSuccess(allEntries);
    }

}
