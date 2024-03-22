package com.zaiming.android.architecture.business.viewmodel

import com.zaiming.android.architecture.base.BaseMvvmViewModel
import com.zaiming.android.architecture.bean.PlayerData
import com.zaiming.android.architecture.business.datacallback.MainCallback
import com.zaiming.android.architecture.business.vmExtension.actions.MainVmExtension
import com.zaiming.android.architecture.business.vmExtension.dispatchers.MainDispatcher
import com.zaiming.android.architecture.business.viewdata.MainViewData

/**
 * Test
 * Main ViewModel
 */
class MainViewModel : BaseMvvmViewModel<MainViewData, MainCallback, MainDispatcher>(
  data = MainViewData(),
  dispatcher = MainVmExtension(),
), MainDispatcher {

  init {
    data.mainLiveData.value = buildList {
      add(PlayerData("Tony", 22))
      add(PlayerData("Jack", 29))
    }
  }

  override fun createDataCallback(): MainCallback = MainDataCallbackEvent()

  override fun onReleaseAction() {
  }

  inner class MainDataCallbackEvent : MainCallback {}

}