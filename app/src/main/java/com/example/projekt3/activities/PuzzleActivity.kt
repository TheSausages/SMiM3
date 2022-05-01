package com.example.projekt3.activities

import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.core.view.descendants
import androidx.lifecycle.MutableLiveData
import com.example.projekt3.R
import com.example.projekt3.models.Puzzle
import com.example.projekt3.models.PuzzleBoardElement
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.time.temporal.Temporal
import java.time.temporal.TemporalUnit
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class PuzzleActivity: AppCompatActivity() {
    private lateinit var startTime: Instant

    companion object {
        const val NUMBER_ROWS = 4
        const val NUMBER_COLUMNS = 3
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.puzzle_plain)

        // Get the image path
        val imagePath: String = intent.extras?.getString("puzzleImagePath") ?: error("No image path")

        // Find the relevant views
        val boardView: LinearLayout = findViewById(R.id.puzzle_plain_board)
        val puzzleElementsView: ViewGroup = findViewById(R.id.puzzle_plain_elements_grid)

        // Set the weigh of the board to the number of rows
        boardView.weightSum = NUMBER_ROWS.toFloat()

        // Set the min width and height, so when there are no elements it doesn't disappear
        // These are deprecated in API 30, we use min API 24
        puzzleElementsView.minimumWidth = windowManager.defaultDisplay.width
        puzzleElementsView.minimumHeight = windowManager.defaultDisplay.height

        startTime = Instant.now()

        Puzzle.Builder(
            this,
            NUMBER_ROWS,
            NUMBER_COLUMNS,
            imagePath,
            boardView,
            puzzleElementsView
        )
            .onWinning {
                this.finish()
            }
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDestroy() {
        val endTime = Instant.now()

        println(ChronoUnit.SECONDS.between(startTime, endTime))

        super.onDestroy()
    }
}