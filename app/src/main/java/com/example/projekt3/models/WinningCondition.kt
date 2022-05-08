package com.example.projekt3.models

import android.view.ViewGroup
import androidx.core.view.children

interface WinningCondition {
    /**
     * Caution: The board parameter needs to have other ViewGroups as it's children
     */
    fun checkForWin(board: ViewGroup, ifWon: () -> Unit)
}

class DefaultWinningCondition: WinningCondition {
    override fun checkForWin(board: ViewGroup, ifWon: () -> Unit) {
        val completed =
            // Get the rows
            board.children.map { row ->
                if (row !is ViewGroup) error("Child of wrong type detected: ${row.javaClass}")

                // Check win con for all element
                row.children.map { rowElement ->
                    (rowElement as PuzzleBoardElement).checkWinCon()

                // Check if they are all true in row
                }.all { it }
            // Check if all rows return true
            }.all { it }

        if (completed) {
            ifWon()
        }
    }

}