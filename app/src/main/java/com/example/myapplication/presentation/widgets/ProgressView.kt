package com.example.myapplication.presentation.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.myapplication.databinding.WidgetProgressViewBinding

class ProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding = WidgetProgressViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        visibility = View.INVISIBLE
    }

    fun setProgressbarText(text: Int?) {
        text?.run {
            binding.loaderTV.text = context.getString(text)
        }

    }

    fun setProgressbarText(text: String?) {
        text?.run {
            binding.loaderTV.text = text
        }
    }
}