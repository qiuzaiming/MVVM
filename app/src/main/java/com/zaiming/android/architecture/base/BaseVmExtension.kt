package com.zaiming.android.architecture.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.coroutineScope
import com.zaiming.android.architecture.base.interfaces.IBaseDataCallback
import com.zaiming.android.architecture.base.interfaces.IBaseDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseVmExtension<ICB : IBaseDataCallback> : LifecycleOwner, IBaseDispatcher<ICB> {

  private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)
  override val lifecycle: Lifecycle = lifecycleRegistry

  /**
   * Note: do not use in init{} scope because of dataCallback is not initialization.
   */
  protected var dataCallback: ICB? = null

  final override fun setCallback(callback: ICB) {
    dataCallback = callback
  }

  init {
    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
  }

  final override fun onReleaseAction() {
    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    onReleaseInVmExtension()
    dataCallback = null
  }

  open fun onReleaseInVmExtension() {}

  fun launch(
    context: CoroutineContext = EmptyCoroutineContext,
    operation: suspend CoroutineScope.() -> Unit,
  ): Job {
    return lifecycle.coroutineScope.launch(context) {
      operation()
    }
  }
}
