package com.example.movies.menu

import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.text.style.LineBackgroundSpan

class MenuItemUnderlineSpan(
    private val underlineThickness: Float = 3f,
    private val underlineSpace: Int = 7
): LineBackgroundSpan {

    override fun drawBackground(
        canvas: Canvas,
        paint: Paint,
        left: Int,
        right: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        text: CharSequence,
        start: Int,
        end: Int,
        lnum: Int
    ) {
        val textPaint = paint as TextPaint
        val originalColor = textPaint.color
        textPaint.color = textPaint.color
        textPaint.strokeWidth = underlineThickness

        val right = paint.measureText(text.toString())
        canvas.drawLine(
            left.toFloat(),
            bottom.toFloat() + underlineSpace,
            right,
            bottom.toFloat() + underlineSpace,
            textPaint
        )

        textPaint.color = originalColor
        textPaint.strokeWidth = underlineThickness
    }
}