@file:JvmName("ViewKts")

package com.zaiming.android.architecture.utils.extensions

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.annotation.IntRange
import com.zaiming.android.architecture.utils.AntiShakeUtils
import com.zaiming.android.architecture.utils.AntiShakeUtils.MAX_INTERVAL_TIME

fun View.beGone() {
  visibility = View.GONE
}

fun View.beVisible() {
  visibility = View.VISIBLE
}

fun View.beInVisible() {
  visibility = View.INVISIBLE
}

inline fun View.setOnSingleClick(
  @IntRange(from = 0) internalTime: Long = MAX_INTERVAL_TIME,
  crossinline block: View.() -> Unit,
) {
  setOnClickListener {
    if (AntiShakeUtils.isInvalidClick(it, internalTime)) {
      return@setOnClickListener
    }
    block()
  }
}

@SuppressLint("ClickableViewAccessibility")
fun SeekBar.extendSensitivity(defaultPadding: Int = 1000) {
  (this.parent as? ViewGroup)?.setOnTouchListener { _, event ->
    val seekRect = Rect()
    getHitRect(seekRect)
    if (((event.y) >= (top - defaultPadding)) && ((event.y) <= (bottom + defaultPadding))) {
      val y = seekRect.top + seekRect.height() / 2
      var x = event.x - seekRect.left
      if (x < 0) {
        x = 0f
      } else if (x > seekRect.width()) {
        x = seekRect.width().toFloat()
      }
      val me = MotionEvent.obtain(event.downTime, event.eventTime, event.action, x, y.toFloat(), event.metaState)
      return@setOnTouchListener onTouchEvent(me)
    }
    return@setOnTouchListener false
  }
}