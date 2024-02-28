package com.zaiming.android.architecture.ui.fragments

import com.zaiming.android.architecture.R
import com.zaiming.android.architecture.base.BaseMvvmFragment
import com.zaiming.android.architecture.business.viewmodel.MainViewModel
import com.zaiming.android.architecture.databinding.FragmentMainBinding

/**
 * Test
 * Main Fragment
 */
class MainFragment : BaseMvvmFragment<FragmentMainBinding, MainViewModel>(
  R.layout.fragment_main
) {
  override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java
}