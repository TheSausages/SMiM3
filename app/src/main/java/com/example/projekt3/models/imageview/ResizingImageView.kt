package com.example.projekt3.models.imageview

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import com.example.projekt3.listeners.PuzzleLongClickListener

class ResizingImageView(context: Context): androidx.appcompat.widget.AppCompatImageView(context) {
    constructor(imageView: ImageView): this(imageView.context) {
        this.setImageDrawable(imageView.drawable)

        this.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        this.id = imageView.id
        this.contentDescription = imageView.contentDescription
        this.scaleType = ScaleType.FIT_XY
        this.setOnLongClickListener(PuzzleLongClickListener())
    }

    fun toNonResizingImageView(): NonResizingImageView {
        return NonResizingImageView(this)
    }
}