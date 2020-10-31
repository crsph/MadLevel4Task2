package com.example.madlevel4task2

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historyTable")
data class History(

    @ColumnInfo(name = "result")
    val resultText: String,

    @ColumnInfo(name = "computerChoice")
    @DrawableRes val computerChoiceId: Int,

    @ColumnInfo(name = "playerChoice")
    @DrawableRes val playerChoiceId: Int,

    @ColumnInfo(name = "date")
    val dateText: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) {
    companion object {
        val DRAWABLE_IDS = arrayOf(
            R.drawable.rock,
            R.drawable.paper,
            R.drawable.scissors
        )
    }
}