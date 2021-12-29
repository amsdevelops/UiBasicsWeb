package com.example.uibasics

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.core.animation.doOnEnd

class CustomView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attributeSet, defStyle) {
    private var scale = 0f

    private var radius = 0f
    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.RED
    }
    private val animator = ValueAnimator.ofPropertyValuesHolder().apply {
        duration = 1000
        interpolator = AccelerateDecelerateInterpolator()
        addUpdateListener {
            radius = it.getAnimatedValue(KEY_RADIUS) as Float
            alpha = it.getAnimatedValue(KEY_ALPHA) as Float
            invalidate()
        }
        doOnEnd {
            Toast.makeText(context, "Animation finished with scale: $scale", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(width / 2f, height/ 2f, radius, circlePaint)
    }

    fun runCircleAnimation(scale: Float) {
        this.scale = scale

        val radius = width * scale / 2
        val propertyValuesHolderRadius = PropertyValuesHolder.ofFloat(KEY_RADIUS, 0f, radius)
        val propertyValuesHolderAlpha = PropertyValuesHolder.ofFloat(KEY_ALPHA, 0f, scale)
        animator.cancel()
        animator.setValues(propertyValuesHolderRadius, propertyValuesHolderAlpha)
        animator.start()
    }

    companion object {
        private const val KEY_RADIUS = "KEY_RADIUS"
        private const val KEY_ALPHA = "KEY_ALPHA"
    }
}