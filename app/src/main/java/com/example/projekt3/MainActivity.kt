package com.example.projekt3

import android.content.ClipDescription
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.example.projekt3.listeners.CustomDragListener
import com.example.projekt3.listeners.CustomLongClickListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.puzzle_plain)

        val boardView: ViewGroup = findViewById(R.id.puzzle_plain_board)
        val puzzleElementsView: ViewGroup = findViewById(R.id.puzzle_plain_elements)
        val elementView: View = findViewById(R.id.puzzle_element)

        val dragListener = CustomDragListener(this)

        boardView.setOnDragListener(dragListener)
        puzzleElementsView.setOnDragListener(dragListener)

        elementView.setOnLongClickListener(CustomLongClickListener())
    }
}