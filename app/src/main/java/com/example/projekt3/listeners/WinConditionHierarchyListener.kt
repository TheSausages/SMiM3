package com.example.projekt3.listeners

import android.view.View
import android.view.ViewGroup
import com.example.projekt3.models.WinningCondition

class WinConditionHierarchyListener(
    private val boardView: ViewGroup,
    private val winningCondition: WinningCondition,
    private val onWinning: () -> Unit
): ViewGroup.OnHierarchyChangeListener {
    override fun onChildViewAdded(p0: View?, p1: View?) {
        winningCondition.checkForWin(boardView) { onWinning() }
    }

    override fun onChildViewRemoved(p0: View?, p1: View?) { }
}