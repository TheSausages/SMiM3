package com.example.projekt3

import android.content.ClipDescription
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.example.projekt3.activities.PuzzleActivity
import com.example.projekt3.activities.PuzzleSelectorActivity
import com.example.projekt3.listeners.CustomDragListener
import com.example.projekt3.listeners.CustomLongClickListener
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.puzzle_plain)

        val puzzleActivity = Intent(this, PuzzleSelectorActivity::class.java)
        puzzleActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        startActivity(puzzleActivity)
    }
}