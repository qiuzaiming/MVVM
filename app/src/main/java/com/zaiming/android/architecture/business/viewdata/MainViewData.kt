package com.zaiming.android.architecture.business.viewdata

import androidx.lifecycle.MutableLiveData
import com.zaiming.android.architecture.base.BaseViewData
import com.zaiming.android.architecture.bean.PlayerData

/**
 * Test
 * Main ViewData
 */
class MainViewData : BaseViewData() {
  val mainLiveData = MutableLiveData(emptyList<PlayerData>())
}