package com.devrajnish.baseapplication.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

/**
 * Created by Rajnish Sharma on 08-02-2021
 */

abstract class BaseActivity<T : ViewModel, U : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: U
    protected abstract val viewModel: T
    protected open fun init() {}
    protected open fun observers() {}
    protected open fun showLoading(isLoading: Boolean) {}
    protected open fun clickListeners() {}

    private fun getInflatedLayout(inflater: LayoutInflater): View {
        this.binding = setBinding(inflater)
        return binding.root
    }

    abstract fun setBinding(layoutInflater: LayoutInflater): U

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getInflatedLayout(layoutInflater))
        init()
        clickListeners()
        observers()
    }

    fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}