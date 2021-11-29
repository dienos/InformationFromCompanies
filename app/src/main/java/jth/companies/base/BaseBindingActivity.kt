package jth.companies.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindingActivity<T : ViewDataBinding> : BaseActivity() {
    @LayoutRes
    abstract fun getLayoutResId(): Int
    abstract fun initializeViewModel()
    abstract fun initializeEventObserver()

    lateinit var binding: T
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutResId())
        initializeViewModel()
        initializeEventObserver()
    }
}