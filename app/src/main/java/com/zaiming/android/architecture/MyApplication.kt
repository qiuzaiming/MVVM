package com.zaiming.android.architecture

import android.app.Application

class MyApplication : Application() {
  companion object {
    @JvmStatic
    lateinit var instance: Application
      private set
  }

  override fun onCreate() {
    super.onCreate()
    instance = this
  }
}