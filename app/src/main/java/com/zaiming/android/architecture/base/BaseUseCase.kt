package com.zaiming.android.architecture.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class BaseUseCase : LifecycleOwner {

  private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)
  override val lifecycle: Lifecycle = lifecycleRegistry

  init {
    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
  }

  open fun onReleaseAction() {
    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  }

  fun launch(
    context: CoroutineContext = EmptyCoroutineContext,
    operation: suspend CoroutineScope.() -> Unit
  ): Job {
    return lifecycle.coroutineScope.launch(context) {
      operation()
    }
  }
}