package com.zaiming.android.architecture.business.adapter.delegateImpl

import android.content.Context
import android.widget.Toast
import com.zaiming.android.architecture.bean.PlayerData
import com.zaiming.android.architecture.business.adapter.delegateInterfaces.IAdapterMainDelegate

class MainDelegateImpl(private val context: Context) : IAdapterMainDelegate {
  override fun showItemContent(bean: PlayerData) {
    Toast.makeText(context, bean.toString(), Toast.LENGTH_SHORT).show()
  }
}