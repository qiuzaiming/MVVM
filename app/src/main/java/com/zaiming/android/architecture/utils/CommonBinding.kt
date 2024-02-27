package com.zaiming.android.architecture.utils

import android.view.View
import androidx.databinding.BindingAdapter
import com.zaiming.android.architecture.utils.extensions.beGone
import com.zaiming.android.architecture.utils.extensions.beVisible
import com.zaiming.android.architecture.utils.extensions.setOnSingleClick

@BindingAdapter("onSingleClick")
fun View.onSingleClick(listener: View.OnClickListener?) {
  setOnSingleClick {
    listener?.onClick(this)
  }
}

@BindingAdapter("gone")
fun bindGone(view: View, shouldBeGone: Boolean?) {
  if (shouldBeGone == true) {
    view.beGone()
  } else {
    view.beVisible()
  }
}