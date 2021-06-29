package com.example.lightsout.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.lightsout.models.RankingEntry;
import java.util.List;

@Dao
public interface RankingEntryDAO {

    @Query("SELECT * FROM RankingEntry ORDER BY time ASC")
    List<RankingEntry> getAllRankingEntries();

    @Insert
    void insertRankingEntry(RankingEntry newEntry);
}
