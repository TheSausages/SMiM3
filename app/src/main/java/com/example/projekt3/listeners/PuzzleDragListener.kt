package com.example.projekt3.listeners

import android.content.ClipDescription
import android.content.Context
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.projekt3.models.PuzzleBoardElement
import com.example.projekt3.models.imageview.NonResizingImageView
import com.example.projekt3.models.imageview.ResizingImageView

class PuzzleDragListener<T: ImageView>(private val klass: Class<T>): View.OnDragListener {
    override fun onDrag(view: View, dragEvent: DragEvent): Boolean {
        return when(dragEvent.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                dragEvent.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                val v = dragEvent.localState as View
                v.visibility = View.INVISIBLE

                view.invalidate()

                return true
            }

            DragEvent.ACTION_DRAG_LOCATION -> return true

            DragEvent.ACTION_DRAG_EXITED -> {
                view.invalidate()

                val v = dragEvent.localState as View
                v.visibility = View.VISIBLE

                return true
            }

            DragEvent.ACTION_DROP -> {
                view.invalidate()

                val puzzleElement = dragEvent.localState as ImageView

                val destination = view

                if (destination is PuzzleBoardElement && destination.correctPuzzlePieceId != puzzleElement.id) {
                    puzzleElement.visibility = View.VISIBLE

                    return false
                }

                val previousOwner = puzzleElement.parent as ViewGroup
                previousOwner.removeView(puzzleElement)

                if (destination is ViewGroup) {
                    destination.addView(puzzleElement.getCorrectImageViewVersion())
                } else {
                    if (destination.parent is ViewGroup) {
                        (destination.parent as ViewGroup).addView(puzzleElement.getCorrectImageViewVersion())
                    } else {
                        error("Neither the view nor it's parent is a Viewgroup!")
                    }
                }

                puzzleElement.visibility = View.VISIBLE

                return true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                view.invalidate()

                return true
            }

            else -> return false
        }
    }

    private fun ImageView.getCorrectImageViewVersion(): ImageView {
        return when(this::class.java) {
            ResizingImageView::class.java -> {
                if (klass == ResizingImageView::class.java) {
                    this
                } else {
                    NonResizingImageView(this)
                }
            }

            NonResizingImageView::class.java -> {
                if (klass == NonResizingImageView::class.java) {
                    this
                } else {
                    ResizingImageView(this)
                }
            }

            else -> error("This implementation of ImageView should not be used")
        }
    }
}