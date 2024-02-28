package com.zaiming.android.architecture.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DataBindingHolder<DB : ViewDataBinding> : RecyclerView.ViewHolder {
  val binding: DB

  constructor(binding: DB) : super(binding.root) {
    this.binding = binding
  }

  constructor(itemView: View) : super(itemView) {
    binding = DataBindingUtil.bind(itemView)!!
  }

  constructor(@LayoutRes resId: Int, parent: ViewGroup) : this(LayoutInflater.from(parent.context).inflate(resId, parent, false))
}

