package com.zaiming.android.architecture.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.zaiming.android.architecture.BR
import com.zaiming.android.architecture.base.interfaces.IBaseAdapterDelegate

abstract class BaseAdapter<DB : ViewDataBinding, T : Any, D: IBaseAdapterDelegate>(
  private val context: Context,
  @LayoutRes private val layoutId: Int,
  private val delegate: D
) : RecyclerView.Adapter<DataBindingHolder<DB>>() {

  var datas: MutableList<T> = arrayListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingHolder<DB> {
    val dataBindingHolder = DataBindingUtil.inflate<DB>(LayoutInflater.from(context), layoutId, parent, false)
    val viewHolder = DataBindingHolder(dataBindingHolder)
    return viewHolder
  }

  override fun getItemCount(): Int = datas.size

  override fun onBindViewHolder(holder: DataBindingHolder<DB>, position: Int) {
    holder.binding.let {
      val itemData = datas.getOrNull(position)
      itemData?.let { bean ->
        it.setVariable(BR.bean, bean)
        it.setVariable(BR.position, position)
        it.setVariable(BR.delegate, delegate)
        expandBinding(it, position)
        it.executePendingBindings()
      }
    }
  }

  open fun expandBinding(binding: DB, position: Int) {}

  fun setData(list: MutableList<T>?) {
    list?.let {
      datas.clear()
      datas.addAll(it)
    }
  }
}