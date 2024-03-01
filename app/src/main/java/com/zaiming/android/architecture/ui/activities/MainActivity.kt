package com.zaiming.android.architecture.ui.activities

import com.zaiming.android.architecture.R
import com.zaiming.android.architecture.base.BaseMvvmActivity
import com.zaiming.android.architecture.business.adapter.delegateImpl.MainDelegateImpl
import com.zaiming.android.architecture.databinding.ActivityMainBinding
import com.zaiming.android.architecture.business.viewmodel.MainViewModel
import com.zaiming.android.architecture.ui.adapter.MainAdapter

/**
 * Test
 * Main Activity
 */
class MainActivity : BaseMvvmActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

  override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

  override fun initView() {
    binding {
      adapter = MainAdapter(this@MainActivity, MainDelegateImpl(this@MainActivity))
    }
  }
}