package com.example.projekt3.services

import android.content.Context
import android.net.Uri
import java.io.File

fun String.getInternalFilePath(context: Context): String {
    context.let {
        // Create a file object
        val file = File(it.filesDir, this)

        // Check if the file exists
        file.exists().ifFalse { error("Searched file must exist!") }

        // If it exist, read the file as an png image
        return file.path
    }
}

fun <T> Boolean.ifFalse(action: () -> T): T? = if(this) null else action()