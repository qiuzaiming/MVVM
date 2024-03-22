package com.zaiming.android.architecture.utils

import android.annotation.SuppressLint
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zaiming.android.architecture.base.adapter.BaseAdapter
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

@Suppress("UNCHECKED_CAST")
@SuppressLint("NotifyDataSetChanged")
@BindingAdapter("notify")
fun <T : Any> bindRecyclerView(recyclerView: RecyclerView, list: MutableList<T>?) {
  (recyclerView.adapter as? BaseAdapter<*, T, *>)?.let {
    it.setData(list)
    it.notifyDataSetChanged()
  }
}

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
  view.adapter = adapter
}