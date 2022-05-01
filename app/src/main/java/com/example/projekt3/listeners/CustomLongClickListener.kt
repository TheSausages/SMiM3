package com.example.projekt3.listeners

import android.content.ClipData
import android.content.ClipDescription
import android.view.View

class CustomLongClickListener: View.OnLongClickListener {
    override fun onLongClick(view: View): Boolean {
        val clipText = ""
        val clipItem = ClipData.Item(clipText)
        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
        val data = ClipData(clipText, mimeTypes, clipItem)

        val dragShadowBuilder = View.DragShadowBuilder(view)
        view.startDragAndDrop(data, dragShadowBuilder, view, 0)

        view.visibility = View.INVISIBLE

        return true
    }
}