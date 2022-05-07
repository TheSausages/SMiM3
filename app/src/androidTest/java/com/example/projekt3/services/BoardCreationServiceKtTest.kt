package com.example.projekt3.services

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.projekt3.activities.PuzzleSelectorActivity
import com.example.projekt3.models.PuzzleBoardElement
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.Every
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class BoardCreationServiceKtTest: TestCase() {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(PuzzleSelectorActivity::class.java)

    private val expectedLinearParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT,
        1F
    )

    @Test
    fun createBoard__NoException() {
        //given
        val nrOfRows = 8
        val nrOfColumns = 10

        //when
        val rows: MutableList<ViewGroup> = ArrayList()
        activityScenarioRule.scenario.onActivity {
            rows.addAll(createBoard(it, nrOfRows, nrOfColumns))
        }

        // This is an anti-pattern, but 'hasProperty' does not work in android
        rows.forEach {
            assertThat(it, instanceOf(LinearLayout::class.java))

            val itMapped = it as LinearLayout

            assertThat(itMapped.id, notNullValue())
            assertThat(itMapped.weightSum, equalTo(nrOfColumns.toFloat()))
            assertThat(itMapped.orientation, equalTo(LinearLayout.HORIZONTAL))
            assertThat((itMapped.layoutParams as LinearLayout.LayoutParams).weight, equalTo(expectedLinearParams.weight))
            assertThat((itMapped.layoutParams as LinearLayout.LayoutParams).height, equalTo(expectedLinearParams.height))
            assertThat((itMapped.layoutParams as LinearLayout.LayoutParams).width, equalTo(expectedLinearParams.width))

            assertThat(itMapped.childCount, equalTo(nrOfColumns))
            assertThat(itMapped.children.asIterable(),
                Every.everyItem(allOf(
                    notNullValue(),
                    instanceOf(PuzzleBoardElement::class.java)
                ))
            )
        }
    }

    @Test
    fun createRowLinearView_DefaultDragOnListener_NoException() {
        //given
        val nrOfColumns = 8

        //when
        var createdRowLinearElement: LinearLayout? = null
        activityScenarioRule.scenario.onActivity {
            createdRowLinearElement = createRowLinearView(it, nrOfColumns)
        }

        //then
        assertThat(createdRowLinearElement, allOf(
            notNullValue(),
            instanceOf(LinearLayout::class.java)
        ))

        assertThat(createdRowLinearElement?.id, notNullValue())
        assertThat(createdRowLinearElement?.weightSum, equalTo(nrOfColumns.toFloat()))
        assertThat(createdRowLinearElement?.orientation, equalTo(LinearLayout.HORIZONTAL))
        assertThat((createdRowLinearElement?.layoutParams as LinearLayout.LayoutParams).weight, equalTo(expectedLinearParams.weight))
        assertThat((createdRowLinearElement?.layoutParams as LinearLayout.LayoutParams).height, equalTo(expectedLinearParams.height))
        assertThat((createdRowLinearElement?.layoutParams as LinearLayout.LayoutParams).width, equalTo(expectedLinearParams.width))

        assertThat(createdRowLinearElement?.childCount, equalTo(nrOfColumns))
        assertThat(createdRowLinearElement?.children?.asIterable(),
            Every.everyItem(allOf(
                notNullValue(),
                instanceOf(PuzzleBoardElement::class.java)
            ))
        )
    }

    @Test
    fun testCreateRowLinearView_CustomDragOnListener_NoException() {
        //given
        val nrOfElementsInRow = 5
        val mockOnDragListener: View.OnDragListener = View.OnDragListener { _, _ -> false }

        //when
        var createdRowLinearElement: LinearLayout? = null
        activityScenarioRule.scenario.onActivity {
            createdRowLinearElement = createRowLinearView(it, nrOfElementsInRow, mockOnDragListener)
        }

        //then
        assertThat(createdRowLinearElement, allOf(
            notNullValue(),
            instanceOf(LinearLayout::class.java)
        ))

        assertThat(createdRowLinearElement?.id, notNullValue())
        assertThat(createdRowLinearElement?.weightSum, equalTo(nrOfElementsInRow.toFloat()))
        assertThat(createdRowLinearElement?.orientation, equalTo(LinearLayout.HORIZONTAL))
        assertThat((createdRowLinearElement?.layoutParams as LinearLayout.LayoutParams).weight, equalTo(expectedLinearParams.weight))
        assertThat((createdRowLinearElement?.layoutParams as LinearLayout.LayoutParams).height, equalTo(expectedLinearParams.height))
        assertThat((createdRowLinearElement?.layoutParams as LinearLayout.LayoutParams).width, equalTo(expectedLinearParams.width))

        assertThat(createdRowLinearElement?.childCount, equalTo(nrOfElementsInRow))
        assertThat(createdRowLinearElement?.children?.asIterable(),
            Every.everyItem(allOf(
                notNullValue(),
                instanceOf(PuzzleBoardElement::class.java)
            ))
        )
    }

    @Test
    fun createRowElement_DefaultDragOnListener_NoException() {
        //given

        //when
        var createdRowElement: LinearLayout? = null
        activityScenarioRule.scenario.onActivity {
            createdRowElement = createRowElement(it)
        }

        //then
        assertThat(createdRowElement, allOf(
            notNullValue(),
            instanceOf(PuzzleBoardElement::class.java)
        ))

        assertThat(createdRowElement?.id, notNullValue())
        assertThat(createdRowElement?.gravity, equalTo(Gravity.CENTER))
        assertThat((createdRowElement?.layoutParams as LinearLayout.LayoutParams).weight, equalTo(expectedLinearParams.weight))
        assertThat((createdRowElement?.layoutParams as LinearLayout.LayoutParams).height, equalTo(expectedLinearParams.height))
        assertThat((createdRowElement?.layoutParams as LinearLayout.LayoutParams).width, equalTo(expectedLinearParams.width))
    }

    @Test
    fun createRowElement_CustomDragOnListener_NoException() {
        //given
        val mockOnDragListener: View.OnDragListener = View.OnDragListener { _, _ -> false }

        //when
        var createdRowElement: LinearLayout? = null
        activityScenarioRule.scenario.onActivity {
            createdRowElement = createRowElement(it, mockOnDragListener)
        }

        //then
        assertThat(createdRowElement, allOf(
            notNullValue(),
            instanceOf(PuzzleBoardElement::class.java)
        ))

        assertThat(createdRowElement?.id, notNullValue())
        assertThat(createdRowElement?.gravity, equalTo(Gravity.CENTER))
        assertThat((createdRowElement?.layoutParams as LinearLayout.LayoutParams).weight, equalTo(expectedLinearParams.weight))
        assertThat((createdRowElement?.layoutParams as LinearLayout.LayoutParams).height, equalTo(expectedLinearParams.height))
        assertThat((createdRowElement?.layoutParams as LinearLayout.LayoutParams).width, equalTo(expectedLinearParams.width))
    }
}