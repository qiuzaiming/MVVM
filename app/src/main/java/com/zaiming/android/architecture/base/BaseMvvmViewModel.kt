package com.zaiming.android.architecture.base

import androidx.lifecycle.ViewModel
import com.zaiming.android.architecture.base.interfaces.IBaseDataCallback
import com.zaiming.android.architecture.base.interfaces.IBaseDispatcher

/**
 * Base Mvvm ViewModel
 *
 * @param VD: BaseViewData
 * @param D: IDispatcher<IDataCallback>
 */
abstract class BaseMvvmViewModel<VD: BaseViewData, ICB: IBaseDataCallback, D: IBaseDispatcher<ICB>>(
  val data: VD,
  val dispatcher: D,
) : ViewModel(), IBaseDispatcher<ICB> {

  protected var dataCallback: ICB

  /**
   * active create data callback: ViewModelExtension -> ViewModel
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
    @Suppress("LeakingThis")
    createDataCallback().let {
      dataCallback = it
      setCallback(it)
    }
  }
}