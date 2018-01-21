package com.vlad1m1r.lemniscate.roulette.scribble

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockito_kotlin.doCallRealMethod
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.vlad1m1r.lemniscate.testutils.TestConstants.DELTA
import com.vlad1m1r.lemniscate.testutils.isPeriodic
import com.vlad1m1r.lemniscate.testutils.setupDefaultMock
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.math.PI

@RunWith(MockitoJUnitRunner::class)
class ScribbleProgressViewTest {

    private val view = mock<ScribbleProgressView>()

    @Before
    fun setUp() {
        view.setupDefaultMock()
        doCallRealMethod().whenever(view).radiusSum
        doCallRealMethod().whenever(view).sizeFactor
    }

    @Test
    fun getGraphX() {
        assertThat(view.getGraphX(0.0f)).isWithin(DELTA).of(30.0f)
        assertThat(view.getGraphX(0.1f)).isWithin(DELTA).of(30.589556f)
        assertThat(view.getGraphX(0.5f)).isWithin(DELTA).of(39.26477f)
        assertThat(view.getGraphX(1.0f)).isWithin(DELTA).of(28.14853f)
        assertThat(view.getGraphX(PI.toFloat())).isWithin(DELTA).of(-50.0f)
        assertThat(view.getGraphX(2.0f)).isWithin(DELTA).of(-15.190873f)
        assertThat(view.getGraphX(2 * PI.toFloat())).isWithin(DELTA).of(30.0f)
    }

    @Test
    fun getGraphY() {
        assertThat(view.getGraphY(0.0f)).isWithin(DELTA).of(-10.0f)
        assertThat(view.getGraphY(0.1f)).isWithin(DELTA).of(-5.217273f)
        assertThat(view.getGraphY(0.5f)).isWithin(DELTA).of(23.33849f)
        assertThat(view.getGraphY(1.0f)).isWithin(DELTA).of(40.195274f)
        assertThat(view.getGraphY(PI.toFloat())).isWithin(DELTA).of(-10.0f)
        assertThat(view.getGraphY(2.0f)).isWithin(DELTA).of(37.826897f)
        assertThat(view.getGraphY(2 * PI.toFloat())).isWithin(DELTA).of(-10.0f)
    }

    @Test
    fun isPeriodic() {
        view.isPeriodic(2 * PI.toFloat())
    }
}