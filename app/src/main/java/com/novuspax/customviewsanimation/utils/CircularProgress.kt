package com.novuspax.androidutilities.utils.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CircleProgressView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint()
    private val strokeWidth = 5
    private val backgroundColor = Color.GRAY
    private val foregroundColor = Color.GREEN
    private var progress = 0

    override fun onDraw(canvas: Canvas) {
        // Draw the background circle
        paint.color = backgroundColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth.toFloat()
        paint.isAntiAlias = true
        val radius = Math.min(canvas.width, canvas.height) / 2 - strokeWidth / 2
        canvas.drawCircle(canvas.width / 2f, canvas.height / 2f, radius.toFloat(), paint)

        // Draw the foreground arc
        paint.color = foregroundColor
        val rect = RectF(strokeWidth / 2f, strokeWidth / 2f, canvas.width - strokeWidth / 2f, canvas.height - strokeWidth / 2f)
        canvas.drawArc(rect, -90f, 360f * progress / 100, false, paint)
    }

    fun setProgress(progress: Int) {
        this.progress = progress
        invalidate()
    }
}


class CircleProgressBar(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var progress = 0f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw the circle
        val cx = width / 2f
        val cy = height / 2f
        val radius = Math.min(cx, cy)
        paint.color = Color.BLUE
        canvas.drawCircle(cx, cy, radius, paint)

        // Draw the arc
        paint.color = Color.RED
        val startAngle = -90f
        val sweepAngle = progress * 360 / 100
        canvas.drawArc(cx - radius, cy - radius, cx + radius, cy + radius, startAngle, sweepAngle, false, paint)
    }

    fun setProgress(progress: Int) {
        this.progress = progress.toFloat()
        invalidate()
    }
}

class CustomProgressBarView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val progressBarPaint = Paint()
    private val textPaint = Paint()
    private var progress = 0f
    private var progressBarColor = Color.BLACK
    private var textColor = Color.BLACK
    private var textSize = 50f
    private var padding = 10f
    private var barWidth = 0f
    private var barHeight = 0f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val strokeWidth = 20
    private val backgroundColor = Color.GRAY
    private val foregroundColor = Color.GREEN

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw the circle
        paint.color = backgroundColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth.toFloat()
        paint.isAntiAlias = true
        val radius = Math.min(canvas.width, canvas.height) / 2 - strokeWidth / 2
        canvas.drawCircle(canvas.width / 2f, canvas.height / 2f, radius.toFloat(), paint)

        // Draw the foreground arc
        paint.color = foregroundColor

        val rect = RectF(strokeWidth / 2f, strokeWidth / 2f, canvas.width - strokeWidth / 2f, canvas.height - strokeWidth / 2f)
        canvas.drawArc(rect, 0f, 90f * progress / 100, false, paint)

        // Draw the text
        textPaint.color = textColor
        textPaint.textSize = textSize
        val text = "${progress.toInt()}%"
        val textWidth = textPaint.measureText(text)
        val xPos = (barWidth - textWidth) / 2
        val yPos = (barHeight / 2) - ((textPaint.descent() + textPaint.ascent()) / 2)
        canvas.drawText(text, xPos, yPos, textPaint)
    }

    fun setProgress(progress: Int) {
        this.progress = progress.toFloat()
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        barWidth = w.toFloat()
        barHeight = h.toFloat()
    }
}


