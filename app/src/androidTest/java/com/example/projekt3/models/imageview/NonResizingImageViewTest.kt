package com.example.projekt3.models.imageview

import android.widget.ImageView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.projekt3.activities.PuzzleSelectorActivity
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.core.AllOf
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test

class NonResizingImageViewTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(PuzzleSelectorActivity::class.java)

    @Test
    fun toNonResizingImageView() {
        //given

        //when
        var view: NonResizingImageView? = null
        var testView: ImageView? = null
        activityScenarioRule.scenario.onActivity {
            testView = ImageView(it)
            testView!!.id = ImageView.generateViewId()
            testView!!.contentDescription = "Description"

            view = NonResizingImageView(testView!!)
        }

        //then
        assertThat(view, AllOf.allOf(
            CoreMatchers.notNullValue(),
            CoreMatchers.instanceOf(NonResizingImageView::class.java)
        )
        )

        assertThat(view!!.id, equalTo(testView!!.id))
        assertThat(view!!.contentDescription, equalTo(testView!!.contentDescription))
        assertThat(view!!.scaleType, equalTo(ImageView.ScaleType.FIT_XY))
    }

    @Test
    fun toResizingIMageView() {
        //given

        //when
        var view: ResizingImageView? = null
        var testView: NonResizingImageView? = null
        activityScenarioRule.scenario.onActivity {
            testView = NonResizingImageView(it)
            testView!!.id = ImageView.generateViewId()
            testView!!.contentDescription = "Description"

            view = testView!!.toResizingImageView()
        }

        //then
        assertThat(view, AllOf.allOf(
            CoreMatchers.notNullValue(),
            CoreMatchers.instanceOf(ResizingImageView::class.java))
        )

        assertThat(view!!.id, equalTo(testView!!.id))
        assertThat(view!!.contentDescription, equalTo(testView!!.contentDescription))
        assertThat(view!!.scaleType, equalTo(ImageView.ScaleType.FIT_XY))
    }
}