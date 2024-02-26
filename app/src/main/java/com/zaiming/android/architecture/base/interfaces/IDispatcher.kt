package com.zaiming.android.architecture.base.interfaces

/**
 * Base UI -> ViewModel -> Model
 *
 * @param Cb
 */
interface IDispatcher<Cb> {

  /**
   * data callback function
   * Model -> ViewModel
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