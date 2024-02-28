package com.zaiming.android.architecture.business.adapter.delegateInterfaces

import com.zaiming.android.architecture.base.interfaces.IBaseAdapterDelegate
import com.zaiming.android.architecture.bean.PlayerData
/**
 * Test
 * Adapter Main Delegate interface
 */
interface IAdapterMainDelegate : IBaseAdapterDelegate {

  fun showItemContent(bean: PlayerData)
}