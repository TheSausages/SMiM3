package com.example.projekt3.models

import android.view.ViewGroup
import androidx.core.view.children

interface WinningCondition {
    fun checkForWin(board: ViewGroup, ifWon: () -> Unit)
}

class DefaultWinningCondition: WinningCondition {
    override fun checkForWin(board: ViewGroup, ifWon: () -> Unit) {
        val completed =
            // Get the rows
            (board).children.map { row ->
                (row as ViewGroup).children.map { rowElement ->
                    (rowElement as PuzzleBoardElement).checkWinCon()
                }.all { it }
            }.all { it }

        if (completed) {
            ifWon()
        }
    }

}