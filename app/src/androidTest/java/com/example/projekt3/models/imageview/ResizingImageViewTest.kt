package com.example.projekt3.models.imageview

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.projekt3.activities.PuzzleSelectorActivity
import com.example.projekt3.services.splitImage
import org.hamcrest.CoreMatchers.*
import org.hamcrest.core.AllOf.allOf
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test

class ResizingImageViewTest {
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
        assertThat(view, allOf(
            notNullValue(),
            instanceOf(NonResizingImageView::class.java)
        ))

        assertThat(view!!.id, equalTo(testView!!.id))
        assertThat(view!!.contentDescription, equalTo(testView!!.contentDescription))
        assertThat(view!!.scaleType, equalTo(ImageView.ScaleType.FIT_XY))
    }

    @Test
    fun toNonResizingImageView__NoException() {
        //given

        //when
        var view: NonResizingImageView? = null
        var testView: ResizingImageView? = null
        activityScenarioRule.scenario.onActivity {
            testView = ResizingImageView(it)
            testView!!.id = ImageView.generateViewId()
            testView!!.contentDescription = "Description"

            view = testView!!.toNonResizingImageView()
        }

        //then
        assertThat(view, allOf(
            notNullValue(),
            instanceOf(NonResizingImageView::class.java)
        ))

        assertThat(view!!.id, equalTo(testView!!.id))
        assertThat(view!!.contentDescription, equalTo(testView!!.contentDescription))
        assertThat(view!!.scaleType, equalTo(ImageView.ScaleType.FIT_XY))
//        assertThat(view!!.layoutParams.width, equalTo(expectedLinearParams.width))
//        assertThat(view!!.layoutParams.height, equalTo(expectedLinearParams.height))
    }
}