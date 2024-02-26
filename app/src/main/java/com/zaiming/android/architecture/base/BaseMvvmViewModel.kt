package com.zaiming.android.architecture.base

import androidx.lifecycle.ViewModel
import com.zaiming.android.architecture.base.interfaces.IDispatcher

/**
 * Base Mvvm ViewModel
 *
 * @param VD: BaseViewData
 * @param D: IDispatcher<IDataCallback>
 */
abstract class BaseMvvmViewModel<VD: BaseViewData, D: IDispatcher<*>>(
  val data: VD,
  val dispatcher: D,
) : ViewModel() {

  override fun onCleared() {
    super.onCleared()
    onRelease()
  }

  open fun onRelease() {
    data.lifecycleOwner = null
    dispatcher.onReleaseAction()
  }
}