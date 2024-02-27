package com.zaiming.android.architecture.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.zaiming.android.architecture.BR

abstract class BaseMvvmActivity<VDB : ViewDataBinding, VM : BaseMvvmViewModel<*, *, *>>(
  @LayoutRes private val layoutId: Int,
) : AppCompatActivity() {

  protected lateinit var mBinding: VDB

  protected lateinit var viewModel: VM

  override fun onCreate(savedInstanceState: Bundle?) {
    viewModel = ViewModelProvider(this)[getViewModelClass()]
    super.onCreate(savedInstanceState)
    mBinding = DataBindingUtil.setContentView(this, layoutId)
    mBinding.lifecycleOwner = this

    viewModel.data.lifecycleOwner = this
    mBinding.setVariable(BR.vm, viewModel)
    mBinding.setVariable(BR.viewdata, viewModel.data)

  }

  abstract fun getViewModelClass(): Class<VM>

  protected inline fun binding(block: VDB.() -> Unit): VDB {
    return mBinding.apply(block)
  }
}