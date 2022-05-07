package com.example.projekt3.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt3.R
import com.example.projekt3.adapters.PuzzleSelectorViewAdapter

class PuzzleSelectorActivity: AppCompatActivity() {
    /** Image adapter for the gallery. */
    private lateinit var adapter: PuzzleSelectorViewAdapter

    /** List of paths to the puzzle images. */
    private var images: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.puzzle_selector)

        val recyclerView: RecyclerView = findViewById(R.id.puzzle_selector_id)

        images.add("ranni.jpg")
        images.add("lake.jpg")

        adapter = PuzzleSelectorViewAdapter(this, images)

        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = adapter
    }
}