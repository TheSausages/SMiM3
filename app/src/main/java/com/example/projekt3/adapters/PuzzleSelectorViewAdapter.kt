package com.example.projekt3.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt3.R
import com.example.projekt3.activities.PuzzleActivity

class PuzzleSelectorViewAdapter(
    val context: Context,
    private val puzzleImageView: List<String>
): RecyclerView.Adapter<PuzzleSelectorViewAdapter.PuzzleSelectorViewHolder>() {
    class PuzzleSelectorViewHolder(puzzleTextView: View): RecyclerView.ViewHolder(puzzleTextView) {
        val puzzleSelectorItemView: TextView = puzzleTextView.findViewById(R.id.puzzle_selector_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PuzzleSelectorViewHolder {
        // Inflate Layout in this method which we have created.
        val view: View = LayoutInflater.from(context).inflate(
            R.layout.puzzle_selector_item,
            parent,
            false
        )

        return PuzzleSelectorViewHolder(view)
    }

    override fun onBindViewHolder(holder: PuzzleSelectorViewHolder, position: Int) {
        holder.puzzleSelectorItemView.text = context.resources.getString(R.string.puzzle_selector_text, position + 1, puzzleImageView[position].split(".")[0])

        holder.puzzleSelectorItemView.setOnClickListener {
            val intent = Intent(context, PuzzleActivity::class.java)
            intent.putExtra("puzzleImagePath", puzzleImageView[position])

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return puzzleImageView.size
    }
}