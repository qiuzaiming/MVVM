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
  @LayoutRes private val contentLayoutId: Int,
  private val isShareActivityVm: Boolean = false
) : Fragment() {

  private var _mBinding: VDB? = null

  protected val mBinding: VDB
    get() = checkNotNull(_mBinding) {
      "Fragment $this binding can not be null"
    }

  protected lateinit var viewModel: VM

  final override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View {
    _mBinding = DataBindingUtil.inflate(layoutInflater, contentLayoutId, container, false)
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