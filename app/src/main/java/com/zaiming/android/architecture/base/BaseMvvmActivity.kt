package com.zaiming.android.architecture.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.zaiming.android.architecture.BR
import com.zaiming.android.architecture.utils.annotations.BindingOnly

abstract class BaseMvvmActivity<VDB : ViewDataBinding, VM : BaseMvvmViewModel<*, *, *>>(
  @LayoutRes private val contentLayoutId: Int,
) : AppCompatActivity() {

  @BindingOnly
  protected val binding: VDB by lazy(LazyThreadSafetyMode.NONE) {
    DataBindingUtil.setContentView(this, contentLayoutId)
  }

  protected lateinit var viewModel: VM

  override fun onCreate(savedInstanceState: Bundle?) {
    viewModel = ViewModelProvider(this)[getViewModelClass()]
    super.onCreate(savedInstanceState)
    binding.lifecycleOwner = this

    viewModel.data.lifecycleOwner = this
    binding.setVariable(BR.vm, viewModel)
    binding.setVariable(BR.viewdata, viewModel.data)
    initView()
  }

  abstract fun getViewModelClass(): Class<VM>

  open fun initView() {}

  @BindingOnly
  protected inline fun binding(block: VDB.() -> Unit): VDB {
    return binding.apply(block)
  }
}