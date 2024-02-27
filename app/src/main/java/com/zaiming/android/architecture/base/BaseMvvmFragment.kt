package com.zaiming.android.architecture.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zaiming.android.architecture.BR

abstract class BaseMvvmFragment<VDB : ViewDataBinding, VM : BaseMvvmViewModel<*, *, *>>(
  @LayoutRes private val layoutId: Int,
  private val isShareActivityVm: Boolean = false
) : Fragment() {

  protected lateinit var mBinding: VDB

  protected lateinit var viewModel: VM

  final override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View {
    mBinding = DataBindingUtil.inflate(layoutInflater, layoutId, container, false)
    mBinding.lifecycleOwner = this
    viewModel = ViewModelProvider(if (isShareActivityVm) requireActivity() else this)[getViewModelClass()]
    viewModel.data.lifecycleOwner = this
    mBinding.setVariable(BR.vm, viewModel)
    mBinding.setVariable(BR.viewdata, viewModel.data)
    return mBinding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView()
  }

  abstract fun getViewModelClass(): Class<VM>

  open fun initView() {}
}