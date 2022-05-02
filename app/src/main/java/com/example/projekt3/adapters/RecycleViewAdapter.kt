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

class RecycleViewAdapter(
    val context: Context,
    private val puzzleImageView: List<String>
): RecyclerView.Adapter<RecycleViewAdapter.RecyclerViewHolder>() {
    class RecyclerViewHolder(puzzleTextView: View): RecyclerView.ViewHolder(puzzleTextView) {
        val textView: TextView = puzzleTextView.findViewById(R.id.puzzle_selector_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        // Inflate Layout in this method which we have created.
        val view: View = LayoutInflater.from(context).inflate(
            R.layout.puzzle_selector_item,
            parent,
            false
        )

        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.textView.text = context.resources.getString(R.string.puzzle_selector_text, position + 1, puzzleImageView[position].split(".")[0])

        holder.textView.setOnClickListener {
            val intent = Intent(context, PuzzleActivity::class.java)
            intent.putExtra("puzzleImagePath", puzzleImageView[position])

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return puzzleImageView.size
    }
}