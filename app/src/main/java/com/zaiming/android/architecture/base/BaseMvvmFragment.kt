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
import com.zaiming.android.architecture.utils.annotations.BindingOnly

abstract class BaseMvvmFragment<VDB : ViewDataBinding, VM : BaseMvvmViewModel<*, *, *>>(
  @LayoutRes private val contentLayoutId: Int,
  private val isShareActivityVm: Boolean = false
) : Fragment() {

  private var _binding: VDB? = null

  @BindingOnly
  protected val binding: VDB
    get() = checkNotNull(_binding) {
      "Fragment $this binding can not be null"
    }

  protected lateinit var viewModel: VM

  final override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View {
    _binding = DataBindingUtil.inflate(layoutInflater, contentLayoutId, container, false)
    binding.lifecycleOwner = this
    viewModel = ViewModelProvider(if (isShareActivityVm) requireActivity() else this)[getViewModelClass()]
    viewModel.data.lifecycleOwner = this
    binding.setVariable(BR.vm, viewModel)
    binding.setVariable(BR.viewdata, viewModel.data)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView()
  }

  @BindingOnly
  protected inline fun binding(block: VDB.() -> Unit): VDB {
    return binding.apply(block)
  }

  abstract fun getViewModelClass(): Class<VM>

  open fun initView() {}
}