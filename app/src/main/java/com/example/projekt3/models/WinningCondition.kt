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

                row.children.map { rowElement ->
                    (rowElement as PuzzleBoardElement).checkWinCon()
                }.all { it }
            }.all { it }

        if (completed) {
            ifWon()
        }
    }

}