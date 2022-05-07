package com.example.projekt3.services

import android.content.Context
import android.widget.ImageView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.projekt3.R
import com.example.projekt3.activities.PuzzleSelectorActivity
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.Matchers.everyItem
import org.hamcrest.Matchers.iterableWithSize
import org.hamcrest.core.AllOf.allOf
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.rules.TemporaryFolder
import java.io.File
import java.io.FileOutputStream

class ImageServiceKtTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(PuzzleSelectorActivity::class.java)

    @get:Rule
    var illegalStateExceptionRule: ExpectedException = ExpectedException.none()

    @Test
    fun splitImage_NonExistingImage_ThrowException() {
        // given
        val rowNumber = 5
        val columnNumber = 8
        val filePath = "SomeNonExistingFile"

        // then
        illegalStateExceptionRule.expect(IllegalStateException::class.java)

        //when
        activityScenarioRule.scenario.onActivity {
            splitImage(it, filePath, rowNumber, columnNumber)
        }
    }

    @Test
    fun splitImage_ExistingImage_NoException() {
        // given
        val rowNumber = 5
        val columnNumber = 8
        val filePath = "testFile.jpg"


        //when
        var imageElements: ArrayList<ImageView> = ArrayList()
        activityScenarioRule.scenario.onActivity { activity ->
            // Save the temp image file into the internal storage
            val file = createTestImage(activity, filePath)

            imageElements = splitImage(activity, file.name, rowNumber, columnNumber)

            // Delete it
            file.delete()
        }

        //then
        assertThat(imageElements, allOf(
            notNullValue(),
            iterableWithSize(rowNumber * columnNumber),
            everyItem(allOf(
                notNullValue(),
                instanceOf(ImageView::class.java)
            ))
        ))
    }

    private fun createTestImage(activity: Context, originalFilePath: String): File {
        val file = File(activity.filesDir, originalFilePath)
        FileOutputStream(file).use {
            it.write(
                activity
                    .resources
                    .openRawResource(R.drawable.testfile)
                    .readBytes()
            )
        }

        return file
    }
}