package com.example.projekt3.services

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.projekt3.activities.PuzzleSelectorActivity
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import java.io.File


@RunWith(AndroidJUnit4::class)
class FileServiceKtTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(PuzzleSelectorActivity::class.java)

    @get:Rule
    var illegalStateExceptionRule: ExpectedException = ExpectedException.none()

    @get:Rule
    var tempFolder = TemporaryFolder()

    @Test
    fun getInternalFilePath_NonExistingFile_ThrowException() {
        // given
        val filePath = "SomeNonExistingFile"

        // then
        illegalStateExceptionRule.expect(IllegalStateException::class.java)

        //when
        activityScenarioRule.scenario.onActivity {
            filePath.getInternalFilePath(it)
        }
    }

    @Test
    fun getInternalFilePath_ExistingFile_ThrowException() {
        // given
        val fileName = "tempFile"
        val fileSuffix = ".jpg"

        //when
        activityScenarioRule.scenario.onActivity {
            // Create a temp file
            val tempFile = File.createTempFile(fileName, fileSuffix, it.filesDir)

            // Check if it exists
            tempFile.name.getInternalFilePath(it)

            //delete the temp file
            tempFile.delete()
        }
    }
}