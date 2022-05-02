package com.example.projekt3.listeners

import android.view.View
import android.view.ViewGroup
import com.example.projekt3.models.WinningCondition

class WinConditionHierarchyListener(
    private val boardView: ViewGroup,
    private val winningCondition: WinningCondition,
    private val onWinning: () -> Unit
): ViewGroup.OnHierarchyChangeListener {
    override fun onChildViewAdded(parent: View, child: View) {
        winningCondition.checkForWin(boardView) { onWinning() }
    }

    override fun onChildViewRemoved(parent: View, child: View) { }
}