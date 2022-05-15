package com.example.projekt3.models

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.projekt3.activities.PuzzleSelectorActivity
import com.example.projekt3.services.createBoard
import com.example.projekt3.services.createRowElement
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import java.io.ByteArrayOutputStream
import java.io.PrintStream


@RunWith(AndroidJUnit4::class)
class DefaultWinningConditionTest {
    private val winText = "Won"
    private val ifWin = { print(winText) }
    private val outContent: ByteArrayOutputStream = ByteArrayOutputStream()
    private val originalOut: PrintStream = System.out

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(PuzzleSelectorActivity::class.java)

    @get:Rule
    var illegalStateExceptionRule: ExpectedException = ExpectedException.none()

    @Before
    fun setUpStreams() {
        System.setOut(PrintStream(outContent))
    }

    @After
    fun restoreStreams() {
        System.setOut(originalOut)
    }

    @Test
    fun checkForWin_CorrectStructureButNoWin_NoException() {
        //given
        val nrOfRows = 8
        val nrOfColumns = 10
        var board: ViewGroup? = null
        activityScenarioRule.scenario.onActivity {
            board = LinearLayout(it)

            createBoard(it, nrOfRows, nrOfColumns).forEach(board!!::addView)
        }

        //when
        DefaultWinningCondition().checkForWin(board!!, ifWin)

        //then
        assertThat(outContent.toString(), not(equalTo(winText)))
    }

    @Test
    fun checkForWin_WrongStructureSingleChild_NoException() {
        //given
        var board: ViewGroup? = null
        activityScenarioRule.scenario.onActivity {
            board = LinearLayout(it)

            board!!.addView(ImageView(it))
        }

        //then
        illegalStateExceptionRule.expect(IllegalStateException::class.java)

        //when
        DefaultWinningCondition().checkForWin(board!!, ifWin)
    }

    @Test
    fun checkForWin_WrongStructureManyChildren_NoException() {
        //given
        var board: ViewGroup? = null
        activityScenarioRule.scenario.onActivity {
            board = LinearLayout(it)

            board!!.addView(LinearLayout(it))
            board!!.addView(TextView(it))
        }

        //then
        illegalStateExceptionRule.expect(IllegalStateException::class.java)

        //when
        DefaultWinningCondition().checkForWin(board!!, ifWin)
    }

    @Test
    fun checkForWin_CorrectStructureWithSingleElementNoInserted_NoExceptionWithoutWin() {
        //given
        var board: ViewGroup? = null
        activityScenarioRule.scenario.onActivity {
            board = LinearLayout(it)

            val firstPair = createViewPairForBoard(it)

            val rowView = LinearLayout(it)
            rowView.addView(firstPair.first)

            board!!.addView(rowView)
        }

        //when
        DefaultWinningCondition().checkForWin(board!!, ifWin)

        //then
        assertThat(outContent.toString(), equalTo(""))
    }

    @Test
    fun checkForWin_CorrectStructureWithManyElementsNoInserted_NoExceptionWithoutWin() {
        //given
        var board: ViewGroup? = null
        activityScenarioRule.scenario.onActivity {
            board = LinearLayout(it)

            val firstPair = createViewPairForBoard(it)
            val secondPair = createViewPairForBoard(it)

            val rowView = LinearLayout(it)
            rowView.addView(firstPair.first)
            rowView.addView(secondPair.first)

            board!!.addView(rowView)
        }

        //when
        DefaultWinningCondition().checkForWin(board!!, ifWin)

        //then
        assertThat(outContent.toString(), equalTo(""))
    }

    @Test
    fun checkForWin_CorrectStructureWithManyElementsSingle_NoExceptionWithoutWin() {
        //given
        var board: ViewGroup? = null
        activityScenarioRule.scenario.onActivity {
            board = LinearLayout(it)

            val firstPair = createViewPairForBoard(it)
            val secondPair = createViewPairForBoard(it)

            val rowView = LinearLayout(it)
            rowView.addView(firstPair.first)
            rowView.addView(secondPair.first)

            board!!.addView(rowView)
        }

        //when
        DefaultWinningCondition().checkForWin(board!!, ifWin)

        //then
        assertThat(outContent.toString(), equalTo(""))
    }

    @Test
    fun checkForWin_CorrectStructureWithManyElement_NoExceptionWithoutWin() {
        //given
        var board: ViewGroup? = null
        activityScenarioRule.scenario.onActivity {
            board = LinearLayout(it)

            val firstPair = createViewPairForBoard(it)
            val secondPair = createViewPairForBoard(it)

            firstPair.first.addView(firstPair.second)

            val rowView = LinearLayout(it)
            rowView.addView(firstPair.first)
            rowView.addView(secondPair.first)

            board!!.addView(rowView)
        }

        //when
        DefaultWinningCondition().checkForWin(board!!, ifWin)

        //then
        assertThat(outContent.toString(), equalTo(""))
    }

    @Test
    fun checkForWin_CorrectStructureWithManyElementsSingleInserted_NoExceptionWithoutWin() {
        //given
        var board: ViewGroup? = null
        activityScenarioRule.scenario.onActivity {
            board = LinearLayout(it)

            val firstPair = createViewPairForBoard(it)
            val secondPair = createViewPairForBoard(it)

            firstPair.first.addView(firstPair.second)

            val rowView = LinearLayout(it)
            rowView.addView(firstPair.first)
            rowView.addView(secondPair.first)

            board!!.addView(rowView)
        }

        //when
        DefaultWinningCondition().checkForWin(board!!, ifWin)

        //then
        assertThat(outContent.toString(), equalTo(""))
    }

    @Test
    fun checkForWin_CorrectStructureWithManyElementsManyInsertedNotAll_NoExceptionWithoutWin() {
        //given
        var board: ViewGroup? = null
        activityScenarioRule.scenario.onActivity {
            board = LinearLayout(it)

            val firstPair = createViewPairForBoard(it)
            val secondPair = createViewPairForBoard(it)
            val thirdPair = createViewPairForBoard(it)

            firstPair.first.addView(firstPair.second)
            secondPair.first.addView(secondPair.second)

            val rowView = LinearLayout(it)
            rowView.addView(firstPair.first)
            rowView.addView(secondPair.first)
            rowView.addView(thirdPair.first)

            board!!.addView(rowView)
        }

        //when
        DefaultWinningCondition().checkForWin(board!!, ifWin)

        //then
        assertThat(outContent.toString(), equalTo(""))
    }

    @Test
    fun checkForWin_CorrectStructureWithManyElementsManyInsertedWrongPlacement_NoExceptionWithoutWin() {
        //given
        var board: ViewGroup? = null
        activityScenarioRule.scenario.onActivity {
            board = LinearLayout(it)

            val firstPair = createViewPairForBoard(it)
            val secondPair = createViewPairForBoard(it)
            val thirdPair = createViewPairForBoard(it)

            firstPair.first.addView(firstPair.second)
            secondPair.first.addView(thirdPair.second)
            thirdPair.first.addView(secondPair.second)

            val rowView = LinearLayout(it)
            rowView.addView(firstPair.first)
            rowView.addView(secondPair.first)
            rowView.addView(thirdPair.first)

            board!!.addView(rowView)
        }

        //when
        DefaultWinningCondition().checkForWin(board!!, ifWin)

        //then
        assertThat(outContent.toString(), equalTo(""))
    }

    @Test
    fun checkForWin_CorrectStructureWithManyElements_NoExceptionWithWin() {
        //given
        var board: ViewGroup? = null
        activityScenarioRule.scenario.onActivity {
            board = LinearLayout(it)

            val firstPair = createViewPairForBoard(it)
            val secondPair = createViewPairForBoard(it)

            firstPair.first.addView(firstPair.second)
            secondPair.first.addView(secondPair.second)

            val rowView = LinearLayout(it)
            rowView.addView(firstPair.first)
            rowView.addView(secondPair.first)

            board!!.addView(rowView)
        }

        //when
        DefaultWinningCondition().checkForWin(board!!, ifWin)

        //then
        assertThat(outContent.toString(), equalTo(winText))
    }

    private fun createViewPairForBoard(context: Context): Pair<ViewGroup, View> {
        val boardElementView = createRowElement(context)

        val imageView = ImageView(context)
        imageView.id = ImageView.generateViewId()

        (boardElementView as PuzzleBoardElement).correctPuzzlePieceId = imageView.id

        return Pair(boardElementView, imageView)
    }
}