package com.zaiming.android.architecture.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.coroutineScope
import com.zaiming.android.architecture.base.interfaces.IDataCallback
import com.zaiming.android.architecture.base.interfaces.IDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseUseCase<ICB : IDataCallback> : LifecycleOwner, IDispatcher<ICB> {

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
    onReleaseInUseCase()
    dataCallback = null
  }

  open fun onReleaseInUseCase() {}

  fun launch(
    context: CoroutineContext = EmptyCoroutineContext,
    operation: suspend CoroutineScope.() -> Unit,
  ): Job {
    return lifecycle.coroutineScope.launch(context) {
      operation()
    }
  }
}
