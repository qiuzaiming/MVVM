package com.zaiming.android.architecture.business.viewmodel

import com.zaiming.android.architecture.base.BaseMvvmViewModel
import com.zaiming.android.architecture.business.datacallback.MainCallback
import com.zaiming.android.architecture.business.usecases.actions.MainUseCase
import com.zaiming.android.architecture.business.usecases.dispatchers.MainDispatcher
import com.zaiming.android.architecture.business.viewdata.MainViewData

class MainViewModel : BaseMvvmViewModel<MainViewData, MainCallback, MainDispatcher>(
  data = MainViewData(),
  dispatcher = MainUseCase(),
), MainDispatcher {

  override fun createDataCallback(): MainCallback = MainDataCallbackEvent()

  override fun onReleaseAction() {
  }

  inner class MainDataCallbackEvent : MainCallback {}

}