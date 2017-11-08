/*
 * Copyright 2016 Vladimir Jovanovic
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vlad1m1r.lemniscate.base.models

import android.graphics.Path
import com.vlad1m1r.lemniscate.base.settings.CurveSettings

import com.vlad1m1r.lemniscate.utils.CurveUtils

class DrawState {
    private val STEP_SIZE = 0.001f

    val path = Path()

    private var isExpanding = true
    var currentLineLength: Float = 0.toFloat()
        private set

    private fun addPairOfPointsToPath(start: Point?, end: Point?) {
        if (start != null && end != null) {
            path.moveTo(start.x, start.y)
            path.quadTo(start.x, start.y, end.x, end.y)
        } else if (start != null) {
            path.moveTo(start.x, start.y)
            path.lineTo(start.x, start.y)
        } else if (end != null) {
            path.moveTo(end.x, end.y)
        }
    }

    fun addPointsToPath(listOfPoints: List<Point>, curveSettings: CurveSettings, viewSize: ViewSize) {
        resetPath()

        val holeSize = curveSettings.strokeWidth

        //adds points to path and creates hole if curveSettings.hasHole()
        for (i in listOfPoints.indices) {
            var start: Point? = listOfPoints[i]
            var end: Point? = null


            if (listOfPoints.size > i + 1)
                end = listOfPoints[i + 1]

            if (curveSettings.hasHole) {
                if (start != null && end != null && start.x > end.x) {
                    start = CurveUtils.checkPointForHole(start, holeSize, viewSize.size)
                    end = CurveUtils.checkPointForHole(end, holeSize, viewSize.size)
                }
            }

            addPairOfPointsToPath(start, end)
        }
    }

    private fun resetPath() {
        path.reset()
    }

    fun recalculateLineLength(lineLength: LineLength) {
        if (lineLength.lineMinLength < lineLength.lineMaxLength) {
            if (currentLineLength < lineLength.lineMinLength) {
                currentLineLength = lineLength.lineMinLength
            }
            if (currentLineLength > lineLength.lineMaxLength) {
                currentLineLength = lineLength.lineMaxLength
            }

            if (currentLineLength < lineLength.lineMaxLength && isExpanding) {
                currentLineLength += STEP_SIZE
            } else if (currentLineLength > lineLength.lineMinLength && !isExpanding) {
                currentLineLength -= STEP_SIZE
            } else if (currentLineLength >= lineLength.lineMaxLength) {
                isExpanding = false
            } else if (currentLineLength <= lineLength.lineMinLength) {
                isExpanding = true
            }
        } else {
            currentLineLength = lineLength.lineMaxLength
        }
    }
}
