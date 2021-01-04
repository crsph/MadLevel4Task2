package com.example.madlevel4task2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_history.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HistoryFragment : Fragment() {

    private lateinit var historyRepository: HistoryRepository
    private val historyList = arrayListOf<History>()
    private val historyAdapter = HistoryAdapter(historyList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        historyRepository = HistoryRepository(requireContext())

        getHistoryFromDatabase()
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        rvHistory.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvHistory.adapter = historyAdapter
        rvHistory.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    private fun getHistoryFromDatabase() {
        val history = historyRepository.getAllHistory()
        this@HistoryFragment.historyList.clear()
        this@HistoryFragment.historyList.addAll(history)
        historyAdapter.notifyDataSetChanged()
    }

//    fun dataChanged() {
//        println("Hello")
//        historyAdapter.notifyDataSetChanged()
//        println("Goodbye")
//    }
}