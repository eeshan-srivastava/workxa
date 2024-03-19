package com.example.myapplication.presentation.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.example.domain.model.StandardError
import com.example.myapplication.databinding.WidgetSuperFrameLayoutBinding

class SuperFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var binding: WidgetSuperFrameLayoutBinding =
        WidgetSuperFrameLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    fun setProgressBarVisibility(visibility: Int) {
        binding.progressLayout.visibility = visibility
    }

    fun setProgressLayout(progress: View) {
        binding.progressLayout.addView(progress)
    }

    fun setErrorViewVisibility(visibility: Int) {
        binding.errorView.visibility = visibility

    }

    fun getErrorView(): ErrorView {
        return binding.errorView
    }

    fun setError(error: StandardError, errorViewAction: ErrorView.ErrorViewAction?) {
        binding.errorView.setError(error)
        binding.errorView.setRetry(errorViewAction)
    }

    fun setProgressBarText(loaderText: String?) {
        binding.progressLayout.setProgressbarText(loaderText)
    }
    fun setProgressBarText(loaderText: Int?) {
        binding.progressLayout.setProgressbarText(loaderText)
    }

    fun addEmptyState(view: View) {
        binding.emptyView.addView(view)
    }

    fun setEmptyStateVisibility(visible: Int) {
        binding.emptyView.visibility = visible
    }
}