package com.example.lightsout.data;


import com.example.lightsout.ApplicationController;
import com.example.lightsout.data.tasks.GetAllRankingEntries;
import com.example.lightsout.data.tasks.InsertNewRankingEntry;
import com.example.lightsout.models.RankingEntry;

import java.util.List;

public class RankingEntryRepository {

    private RankingDatabase database;

    public static interface QueryListener
    {
        void onSuccess(List<RankingEntry> allEntries);
    }

    public static interface InsertListener
    {
        void onSuccess();
    }


    public RankingEntryRepository()
    {
        database = ApplicationController.getDatabase();
    }

    public void insertNewRankingEntry(RankingEntry newEntry, InsertListener listener)
    {
        new InsertNewRankingEntry(database,listener).execute(newEntry);
    }

    public void getAllEntries(QueryListener listener)
    {
        new GetAllRankingEntries(database,listener).execute();
    }
}
