package com.example.myapplication.presentation.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.domain.model.StandardError
import com.example.myapplication.databinding.WidgetErrorViewBinding

class
ErrorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var standardError: StandardError? = null

    private val binding =
        WidgetErrorViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        visibility = View.INVISIBLE
    }

    fun setError(error: StandardError, textColor: Int? = null) {
        this.standardError = error
    }

    fun setRetry(listener: ErrorViewAction?) {

    }

    interface ErrorViewAction {
        fun onTapToRetry()
    }
}
