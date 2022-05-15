package com.example.projekt3.models

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.core.view.descendants
import com.example.projekt3.listeners.PuzzleDragListener
import com.example.projekt3.listeners.WinConditionHierarchyListener
import com.example.projekt3.models.imageview.NonResizingImageView
import com.example.projekt3.models.imageview.ResizingImageView
import com.example.projekt3.services.createBoard
import com.example.projekt3.services.splitImage

class PuzzleBoardElement(context: Context): LinearLayout(context) {
    var correctPuzzlePieceId: Int? = -1

    fun checkWinCon(): Boolean {
        return childCount == 1 && children.map { it.id == correctPuzzlePieceId }.all { it }
    }
}

class Puzzle(
    val puzzleElements: List<ImageView>,
    val puzzleRows: List<View>,
) {
    data class Builder(
        val context: Context,
        val numberOfRows: Int,
        val numberOfColumns: Int,
        val imagePath: String,
        val puzzleBoardView: ViewGroup,
        val puzzlePiecesHolderView: ViewGroup,
        var winningCondition: WinningCondition = DefaultWinningCondition(),
        var puzzlePiecesOnDragListener: View.OnDragListener = PuzzleDragListener(ResizingImageView::class.java),
        var puzzlePiecesHolderOnDragListener: View.OnDragListener = PuzzleDragListener(NonResizingImageView::class.java),
        var onWinning: () -> Unit = { println("WON!") }
    ) {
        fun puzzlePiecesOnDragListener(puzzlePiecesListener: View.OnDragListener) =
            apply { this.puzzlePiecesOnDragListener = puzzlePiecesListener }

        fun puzzlePiecesHolderOnDragListener(puzzlePiecesHolderListener: View.OnDragListener) =
            apply { this.puzzlePiecesHolderOnDragListener = puzzlePiecesHolderListener }

        fun winningStrategy(strategy: WinningCondition) = apply { this.winningCondition = strategy }

        fun onWinning(onWin: () -> Unit) = apply { this.onWinning = onWin }

        fun build(): Puzzle {
            val rowList = createBoard(context, numberOfRows, numberOfColumns)

            val piecesList = splitImage(context, imagePath, numberOfRows, numberOfColumns)

            for (row in rowList.indices) {
                val rowToAdd = row * numberOfColumns

                rowList[row].children.forEachIndexed { index, it ->
                    (it as PuzzleBoardElement).correctPuzzlePieceId = piecesList[rowToAdd + index].id

                    it.setOnHierarchyChangeListener(WinConditionHierarchyListener(
                        puzzleBoardView,
                        winningCondition,
                        onWinning
                    ))
                }
            }

            piecesList.shuffle()
            piecesList.forEach {
                it.setOnDragListener(puzzlePiecesOnDragListener)
                puzzlePiecesHolderView.addView(it)
            }

            puzzleBoardView.descendants
            rowList.forEach { puzzleBoardView.addView(it) }

            puzzlePiecesHolderView.setOnDragListener(puzzlePiecesHolderOnDragListener)

            return Puzzle(piecesList, rowList)
        }
    }
}