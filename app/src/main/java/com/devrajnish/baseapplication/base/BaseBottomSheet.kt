package com.devrajnish.baseapplication.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Created by Rajnish Sharma on 08-02-2021
 */

abstract class BaseBottomSheet: BottomSheetDialogFragment() {
    private var activity: AppCompatActivity? = null

    @LayoutRes
    protected abstract fun layoutRes(): Int
    protected abstract fun tag(): String
    protected open fun init() {}
    protected open fun setOnClickListeners() {}

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layoutRes(), container, false)
        view.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setOnClickListeners()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as AppCompatActivity
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    fun show(fragmentManager: FragmentManager) {
        show(fragmentManager, tag())
    }
}