package com.example.projekt3.models.imageview

import android.content.Context
import android.graphics.Bitmap
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.ImageView
import com.example.projekt3.listeners.PuzzleLongClickListener

class NonResizingImageView(context: Context): androidx.appcompat.widget.AppCompatImageView(context) {
    companion object {
        const val SIZE = 105F

        fun create(
            context: Context,
            bitmap: Bitmap,
            contentDescription: String
        ): NonResizingImageView {
            val imageView = NonResizingImageView(context)
            imageView.setImageBitmap(bitmap)

            imageView.layoutParams = ViewGroup.LayoutParams(
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, SIZE, context.resources.displayMetrics).toInt(),
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, SIZE, context.resources.displayMetrics).toInt()
            )
            imageView.id = ImageView.generateViewId()
            imageView.contentDescription = contentDescription
            imageView.scaleType = ScaleType.FIT_XY
            imageView.setOnLongClickListener(PuzzleLongClickListener())

            return imageView
        }
    }

    constructor(imageView: ImageView) : this(imageView.context) {
        this.setImageDrawable(imageView.drawable)
        this.layoutParams = ViewGroup.LayoutParams(
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, SIZE, context.resources.displayMetrics).toInt(),
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, SIZE, context.resources.displayMetrics).toInt()
        )

        this.id = imageView.id
        this.contentDescription = imageView.contentDescription
        this.scaleType = ScaleType.FIT_XY
        this.setOnLongClickListener(PuzzleLongClickListener())
    }

    fun toResizingImageView(): ResizingImageView {
        return ResizingImageView(this)
    }
}