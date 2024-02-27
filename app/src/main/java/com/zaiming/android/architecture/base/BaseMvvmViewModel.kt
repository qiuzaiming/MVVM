package com.zaiming.android.architecture.base

import androidx.lifecycle.ViewModel
import com.zaiming.android.architecture.base.interfaces.IDataCallback
import com.zaiming.android.architecture.base.interfaces.IDispatcher

/**
 * Base Mvvm ViewModel
 *
 * @param VD: BaseViewData
 * @param D: IDispatcher<IDataCallback>
 */
abstract class BaseMvvmViewModel<VD: BaseViewData, ICB: IDataCallback, D: IDispatcher<ICB>>(
  val data: VD,
  val dispatcher: D,
) : ViewModel(), IDispatcher<ICB> {

  protected var dataCallback: ICB

  /**
   * active create data callback: UseCase -> ViewModel
   *
   */
  protected abstract fun createDataCallback(): ICB

  final override fun onCleared() {
    super.onCleared()
    onRelease()
  }

  final override fun setCallback(callback: ICB) {
    dispatcher.setCallback(callback)
  }

  open fun onRelease() {
    data.lifecycleOwner = null
    dispatcher.onReleaseAction()
  }

  init {
    createDataCallback().let {
      dataCallback = it
      setCallback(it)
    }
  }
}