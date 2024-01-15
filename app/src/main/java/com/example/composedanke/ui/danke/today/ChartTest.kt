package com.example.composedanke.ui.danke.today

import android.content.Context
import android.graphics.Paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

class ChartTest(private val drawScope: DrawScope, mContext: Context) {
    /** paint, path*/
    private val baseLineXPaint: Paint = Paint()
    private val baseGraduationPaint: Paint = Paint()

    init {
        baseLineXPaint.apply {
            strokeWidth = dpiToPixels(mContext, 6)
            style = Paint.Style.FILL
            color = android.graphics.Color.GRAY
            isAntiAlias = true
        }
        baseGraduationPaint.apply {
            strokeWidth = dpiToPixels(mContext, 1)
            style = Paint.Style.STROKE
//            color = graduationColor
            isAntiAlias = true
        }
    }

    fun drawCircle(color: Color, radius: Float, center: Offset) {
        drawScope.drawCircle(
            color = color,
            radius = radius,
            center = center,
        )
    }
}