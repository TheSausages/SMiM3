package com.example.projekt3.services

import android.content.Context
import android.widget.Toast
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

const val PATHS_FILE = "paths.txt"
const val DEFAULT_PUZZLE_IMAGES_FOLDER_NAME = "images"
val PATHS_FILE_CHARSET: Charset = Charset.forName("UTF-8")
val DEFAULT_PUZZLE_IMAGES = arrayListOf(
    "lake.jpg",
    "ranni.jpg",
    "cat.jpg",
    "black_hole.jpg",
    "car.jpg",
    "house.jpg",
    "mountines.jpg",
    "steppe.jpg",
    "wave.jpg",
    "river.jpg"
)

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

fun getPuzzlePaths(context: Context): List<String> {
    context.let {
        try {
            val file = File(it.filesDir, PATHS_FILE)

            // If the file doesn't exist, create it
            if (file.exists()) {
                // Create an Input Stream
                val inputStream = FileInputStream(file)

                // Create a reader for the stream
                val fileReader = InputStreamReader(inputStream, PATHS_FILE_CHARSET)

                // Read all lines and map them to Strings
                val puzzlePifePaths: List<String> = fileReader.readLines()

                // Close the file stream
                inputStream.close()

                //return the list
                return puzzlePifePaths
            } else {
                // If no file exist, create it and return an empty list
                file.createNewFile()

                file.writeText(
                    DEFAULT_PUZZLE_IMAGES
                        .joinToString("\n") {
                                fileName -> "$DEFAULT_PUZZLE_IMAGES_FOLDER_NAME/$fileName"
                        }
                )

                return DEFAULT_PUZZLE_IMAGES
            }
        } catch (e: Exception) {
            Toast.makeText(it, "Could not read puzzle paths", Toast.LENGTH_SHORT).show()
        }

        return ArrayList()
    }
}

fun <T> Boolean.ifFalse(action: () -> T): T? = if(this) null else action()