package com.example.madlevel4task2

import androidx.room.*

@Dao
interface HistoryDao {

    @Query("SELECT * FROM historyTable")
    fun getAllHistory(): List<History>

    @Query("DELETE FROM historyTable")
    fun nukeTable()

    @Insert
    fun insertHistory(history: History)

    @Query("SELECT COUNT(id) FROM historyTable WHERE result = 'You win!'")
    fun getWins(): Int

    @Query("SELECT COUNT(id) FROM historyTable WHERE result = 'Draw'")
    fun getDraws(): Int

    @Query("SELECT COUNT(id) FROM historyTable WHERE result = 'Computer wins!'")
    fun getLoses(): Int
}
