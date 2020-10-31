package com.example.madlevel4task2

import android.content.Context

public class HistoryRepository(context: Context) {

    private var historyDao: HistoryDao

    init {
        val historyRoomDatabase = HistoryRoomDatabase.getDatabase(context)
        historyDao = historyRoomDatabase!!.historyDao()
    }

    fun getAllHistory(): List<History> {
        return historyDao.getAllHistory()
    }

    fun getWins(): Int {
        return historyDao.getWins()
    }

    fun getDraws(): Int {
        return historyDao.getDraws()
    }

    fun getLoses(): Int {
        return historyDao.getLoses()
    }

    fun insertHistory(history: History) {
        historyDao.insertHistory(history)
    }

    fun deleteHistory() {
        historyDao.nukeTable()
    }
}
