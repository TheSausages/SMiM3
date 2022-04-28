package com.example.projekt3.listeners

import android.content.ClipDescription
import android.content.Context
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast

class CustomDragListener(private val context: Context): View.OnDragListener {
    override fun onDrag(view: View, dragEvent: DragEvent): Boolean {
        return when(dragEvent.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                dragEvent.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                view.invalidate()

                return true
            }

            DragEvent.ACTION_DRAG_LOCATION -> return true

            DragEvent.ACTION_DRAG_EXITED -> {
                view.invalidate()

                return true
            }

            DragEvent.ACTION_DROP -> {
                val item = dragEvent.clipData.getItemAt(0)
                val dragData = item.text
                Toast.makeText(context, dragData, Toast.LENGTH_SHORT).show()

                view.invalidate()

                val v = dragEvent.localState as View

                val previousOwner = v.parent as ViewGroup
                previousOwner.removeView(v)

                val destination = view as LinearLayout
                destination.addView(v)

                v.visibility = View.VISIBLE

                return true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                view.invalidate()

                return true
            }

            else -> return false
        }
    }
}