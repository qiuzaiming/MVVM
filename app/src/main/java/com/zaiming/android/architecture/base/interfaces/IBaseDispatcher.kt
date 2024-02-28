package com.zaiming.android.architecture.base.interfaces

/**
 * Base UI -> ViewModel -> UseCase
 *
 * @param Cb
 */
interface IBaseDispatcher<Cb: IBaseDataCallback> {

  /**
   * data callback function
   * UseCase -> ViewModel
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