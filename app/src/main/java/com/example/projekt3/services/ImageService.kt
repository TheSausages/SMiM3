package com.example.projekt3.services

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import com.example.projekt3.models.imageview.NonResizingImageView

// http://www.chansek.com/splittingdividing-image-into-smaller/
fun splitImage(context: Context, imagePath: String, rows: Int, cols: Int): ArrayList<ImageView> {
    //For height and width of the small image chunks
    val chunkHeight: Int
    val chunkWidth: Int

    //To store all the small image chunks in bitmap format in this list
    val chunkedImages = ArrayList<ImageView>(rows * cols)

    //Getting the scaled bitmap of the source image
    val drawable = BitmapDrawable.createFromPath(imagePath.getInternalFilePath(context)) as BitmapDrawable
    val bitmap = drawable.bitmap
    val scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.width, bitmap.height, true)
    chunkHeight = bitmap.height / rows
    chunkWidth = bitmap.width / cols

    //xCoord and yCoord are the pixel positions of the image chunks
    var yCoord = 0
    for (x in 0 until rows) {
        var xCoord = 0
        for (y in 0 until cols) {
            chunkedImages.add(createImageChunkView(
                context,
                scaledBitmap,
                xCoord,
                yCoord,
                chunkWidth,
                chunkHeight
            ))

            xCoord += chunkWidth
        }
        yCoord += chunkHeight
    }

    return chunkedImages
}

fun createImageChunkView(
    context: Context,
    bitmap: Bitmap,
    xCoord: Int,
    yCoord: Int,
    chunkWidth: Int,
    chunkHeight: Int
): ImageView = NonResizingImageView.create(
    context,
    Bitmap.createBitmap(
        bitmap,
        xCoord,
        yCoord,
        chunkWidth,
        chunkHeight
    ),
    "Element of bigger picture of coords ($xCoord;$yCoord)")