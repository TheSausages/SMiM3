package com.example.projekt3.services

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.projekt3.listeners.PuzzleDragListener
import com.example.projekt3.models.PuzzleBoardElement
import com.example.projekt3.models.imageview.ResizingImageView
import java.util.*

private val RANDOM = Random()

fun createBoard(context: Context, numberOfRows: Int, numberOfColumns: Int): List<ViewGroup> {
    val rowList = ArrayList<ViewGroup>()
    repeat(numberOfRows) { rowList.add(createRowLinearView(context, numberOfColumns)) }

    return rowList
}

fun createRowLinearView(context: Context, numOfElementsInRow: Int): LinearLayout =
    createRowLinearView(context, numOfElementsInRow, PuzzleDragListener(ResizingImageView::class.java))

fun createRowLinearView(context: Context, numOfElementsInRow: Int, dragListener: View.OnDragListener): LinearLayout {
    val row = LinearLayout(context)
    row.layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT,
        1F
    )
    row.id = LinearLayout.generateViewId()
    row.weightSum = numOfElementsInRow.toFloat()
    row.orientation = LinearLayout.HORIZONTAL

    repeat(numOfElementsInRow) {
        row.addView(createRowElement(context, dragListener))
    }

    return row
}

fun createRowElement(context: Context): LinearLayout =
    createRowElement(context, PuzzleDragListener(ResizingImageView::class.java))

fun createRowElement(context: Context, dragListener: View.OnDragListener): LinearLayout {
    val element = PuzzleBoardElement(context)
    element.layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT,
        1F
    )
    element.id = LinearLayout.generateViewId()
    element.setOnDragListener(dragListener)
    element.gravity = Gravity.CENTER
    element.setBackgroundColor(Color.argb(255, RANDOM.nextInt(256), RANDOM.nextInt(256), RANDOM.nextInt(256)))

    return element
}