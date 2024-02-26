package com.zaiming.android.architecture.business.usecases.actions

import com.zaiming.android.architecture.base.BaseUseCase
import com.zaiming.android.architecture.business.datacallback.MainCallback
import com.zaiming.android.architecture.business.usecases.dispatchers.MainDispatcher

class MainUseCase : BaseUseCase(), MainDispatcher {

  private var callback: MainCallback? = null

  override fun setCallback(callback: MainCallback) {
    this.callback = callback
  }

  override fun onReleaseAction() {
    super.onReleaseAction()
    callback = null
  }

}