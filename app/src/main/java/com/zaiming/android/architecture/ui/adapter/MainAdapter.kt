package com.zaiming.android.architecture.ui.adapter

import android.content.Context
import com.zaiming.android.architecture.R
import com.zaiming.android.architecture.base.adapter.BaseAdapter
import com.zaiming.android.architecture.bean.PlayerData
import com.zaiming.android.architecture.databinding.AdapterMainBinding
import com.zaiming.android.architecture.business.adapter.delegateInterfaces.IAdapterMainDelegate

/**
 * Test
 * Main Adapter
 */
class MainAdapter(
  context: Context,
  delegate: IAdapterMainDelegate,
) : BaseAdapter<AdapterMainBinding, PlayerData, IAdapterMainDelegate>(
  context = context,
  layoutId = R.layout.adapter_main,
  delegate = delegate
)