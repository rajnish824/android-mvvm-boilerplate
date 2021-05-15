package com.devrajnish.baseapplication.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.devrajnish.baseapplication.R

/**
 * Created by Rajnish Sharma on 08-02-2021
 */

abstract class BaseDialog : DialogFragment() {
    private var activity: AppCompatActivity? = null

    @LayoutRes
    protected abstract fun layoutRes(): Int
    protected open fun init() {}
    protected open fun setOnClickListener() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes(), container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window?.setGravity(Gravity.BOTTOM)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as AppCompatActivity
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    override fun onResume() {
        super.onResume()
        val params: WindowManager.LayoutParams? = dialog?.window?.attributes
        dialog?.window?.attributes?.width = LinearLayout.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes?.height = LinearLayout.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog?.window?.attributes = params
    }

}