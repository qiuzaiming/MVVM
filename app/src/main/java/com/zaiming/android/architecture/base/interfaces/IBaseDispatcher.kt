package com.zaiming.android.architecture.base.interfaces

/**
 * Base UI -> ViewModel -> ViewModelExtension
 *
 * @param Cb
 */
interface IBaseDispatcher<Cb: IBaseDataCallback> {

  /**
   * data callback function
   * ViewModelExtension -> ViewModel
   *
   * @param callback
   */
  fun setCallback(callback: Cb)

  /**
   * release resource
   *
   */
  fun onReleaseAction()
}