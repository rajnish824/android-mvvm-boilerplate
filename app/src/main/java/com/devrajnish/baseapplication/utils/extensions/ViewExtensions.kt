package com.devrajnish.baseapplication.utils.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.devrajnish.baseapplication.R
import com.google.android.material.textfield.TextInputLayout
import kotlin.math.roundToInt

/**
 * Created by Rajnish Sharma on 08-02-2021
 */

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.show(enable: Boolean) {
    visibility = if (enable) View.VISIBLE else View.GONE
}

fun View.invisible(enable: Boolean) {
    visibility = if (enable) View.INVISIBLE else View.VISIBLE
}

fun View.isVisible() = visibility == View.VISIBLE

fun TextView.setColor(color: Int) {
    setTextColor(color)
}

fun ImageView.setColor(color: Int) {
    setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN)
}

fun View.setBackgroundTint(color: Int) {
    backgroundTintList = ContextCompat.getColorStateList(context, color)
}

fun AlertDialog.show(enable: Boolean) {
    if (enable) show() else dismiss()
}

fun ImageView.parseColor(color: String?, fallback: Int) {
    try {
        setColor(Color.parseColor(color))
    } catch (e: Exception) {
        setImageDrawable(ContextCompat.getDrawable(context, fallback))
    }
}

fun TextInputLayout.clearError() {
    error = null
    isErrorEnabled = false
}

fun EditText.setNullableText(text: String?) {
    text?.let { setText(it) }
}

fun EditText.setNullableText(text: Int?) {
    text?.let { setText(it.toString()) }
}

fun View.changeColor(color: Int) {
    this.background.setTintList(ContextCompat.getColorStateList(context, color))
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun RecyclerView.setScrollListener(action: (dx: Int, dy: Int) -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            action.invoke(dx, dy)
        }
    })
}

fun View.hideAnimation(isTopToDown: Boolean = false, time: Long = 300) {
    val animation = AnimationUtils.loadAnimation(
        context,
        if (isTopToDown) R.anim.slide_down_hide else R.anim.slide_up_hide
    )
    animation.duration = time

    startAnimation(animation)
    animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(p0: Animation?) {}

        override fun onAnimationEnd(p0: Animation?) {
            hide()
            animation.setAnimationListener(null)
        }

        override fun onAnimationRepeat(p0: Animation?) {}
    })
}

fun View.showAnimation(isTopToDown: Boolean = false, time: Long = 300) {
    show()
    val animation = AnimationUtils.loadAnimation(
        context,
        if (isTopToDown) R.anim.slide_down else R.anim.slide_up
    )
    animation.duration = time
    startAnimation(animation)
}

fun View.showAnimationHorizontal(isLeftToRight: Boolean = true) {
    show()
    startAnimation(
        AnimationUtils.loadAnimation(
            context,
            if (isLeftToRight) R.anim.slide_right else R.anim.slide_left
        )
    )
}

fun View.hideAnimationHorizontal(isLeftToRight: Boolean = true) {
    val animation = AnimationUtils.loadAnimation(
        context,
        if (isLeftToRight) R.anim.slide_right_hide else R.anim.slide_left_hide
    )

    startAnimation(animation)
    animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(p0: Animation?) {}

        override fun onAnimationEnd(p0: Animation?) {
            hide()
            animation.setAnimationListener(null)
        }

        override fun onAnimationRepeat(p0: Animation?) {}
    })
}

fun Int.manipulateColor(factor: Float): Int {
    val a = Color.alpha(this)
    val r = (Color.red(this) * factor).roundToInt()
    val g = (Color.green(this) * factor).roundToInt()
    val b = (Color.blue(this) * factor).roundToInt()
    return Color.argb(
        a,
        r.coerceAtMost(255),
        g.coerceAtMost(255),
        b.coerceAtMost(255)
    )
}

fun ImageView.greyScale(saturation: Float = 0f) {
    val matrix = ColorMatrix().apply {
        setSaturation(saturation)
    }
    colorFilter = ColorMatrixColorFilter(matrix)
}

fun ImageView.resetScale() {
    colorFilter = null
}