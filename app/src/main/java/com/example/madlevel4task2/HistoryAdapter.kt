package com.example.madlevel4task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(private val history: List<History>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun databind(history: History) {
            itemView.tvResult.text = history.resultText
            itemView.ivPlayerResult.setImageResource(History.DRAWABLE_IDS[history.playerChoiceId])
            itemView.ivComputerResult.setImageResource(History.DRAWABLE_IDS[history.computerChoiceId])
            itemView.tvDate.text = history.dateText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        return holder.databind(history[position])
    }

    override fun getItemCount(): Int {
        return history.size
    }

}