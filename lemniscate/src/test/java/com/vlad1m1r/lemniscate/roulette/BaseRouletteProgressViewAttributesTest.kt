package com.vlad1m1r.lemniscate.roulette

import com.google.common.truth.Truth.assertThat
import com.vlad1m1r.lemniscate.R
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class BaseRouletteProgressViewAttributesTest {

    val context = RuntimeEnvironment.application.applicationContext
    val atributeSet =  Robolectric.buildAttributeSet()
            .addAttribute(R.attr.radiusFixed, "34")
            .addAttribute(R.attr.radiusMoving, "23")
            .addAttribute(R.attr.numberOfCycles, "43")
            .addAttribute(R.attr.distanceFromCenter, "31")
            .build()

    @Test
    fun constructorWithAttributeSet() {
        val epitrochoidProgressView = EpitrochoidProgressView(context, atributeSet)
        assertThat(epitrochoidProgressView.radiusFixed).isEqualTo(34f)
        assertThat(epitrochoidProgressView.radiusMoving).isEqualTo(23f)
        assertThat(epitrochoidProgressView.numberOfCycles).isEqualTo(43f)
        assertThat(epitrochoidProgressView.distanceFromCenter).isEqualTo(31f)
    }
}
