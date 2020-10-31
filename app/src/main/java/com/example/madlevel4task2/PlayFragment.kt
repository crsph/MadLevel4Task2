package com.example.madlevel4task2

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_play.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class PlayFragment : Fragment() {

    private lateinit var historyRepository: HistoryRepository
    private lateinit var currentTime: String
    private var playerPickId: Int = 0
    private var computerPickId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historyRepository = HistoryRepository(requireContext())

        updateScore()

        ivRock.setOnClickListener {
            playerPickId = 0
            computerPickId = (0..2).random()

            playGame(playerPickId, computerPickId)
            updateScore()
        }

        ivPaper.setOnClickListener {
            playerPickId = 1
            computerPickId = (0..2).random()

            playGame(playerPickId, computerPickId)
            updateScore()
        }

        ivScissor.setOnClickListener {
            playerPickId = 2
            computerPickId = (0..2).random()

            playGame(playerPickId, computerPickId)
            updateScore()
        }
    }

    /**
     * Sets the chosen ImageView and insert the result, chosen ImageView, and date into the database
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun playGame(playerChoice: Int, computerChoice: Int) {
        ivPlayerPick.setImageResource(History.DRAWABLE_IDS[playerChoice])
        ivComputerPick.setImageResource(History.DRAWABLE_IDS[computerChoice])

        // Retrieves the current time
        currentTime = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        ).toString()

        // Checks which TextView the place and computer chose and displays the result as TextView
        when (playerChoice) {
            0 -> {
                when (computerPickId) {
                    0 -> {
                        tvResult.text = getString(R.string.you_draw)
                    }
                    1 -> {
                        tvResult.text = getString(R.string.computer_wins)
                    }
                    else -> {
                        tvResult.text = getString(R.string.you_win)
                    }
                }
            }
            1 -> {
                when (computerPickId) {
                    0 -> {
                        tvResult.text = getString(R.string.you_win)
                    }
                    1 -> {
                        tvResult.text = getString(R.string.you_draw)
                    }
                    else -> {
                        tvResult.text = getString(R.string.computer_wins)
                    }
                }
            }
            2 -> {
                when (computerPickId) {
                    0 -> {
                        tvResult.text = getString(R.string.computer_wins)
                    }
                    1 -> {
                        tvResult.text = getString(R.string.you_win)
                    }
                    else -> {
                        tvResult.text = getString(R.string.you_draw)
                    }
                }
            }
        }

        // Create object
        val history = History(tvResult.text.toString(), computerChoice, playerChoice, currentTime)

        // Insert into the database
        historyRepository.insertHistory(history)
    }

    /**
     * Update the score
     */
    private fun updateScore() {
        val wins = historyRepository.getWins()
        val draws = historyRepository.getDraws()
        val loses = historyRepository.getLoses()

        tvWinScore.text = wins.toString()
        tvDrawScore.text = draws.toString()
        tvLoseScore.text = loses.toString()
    }

}