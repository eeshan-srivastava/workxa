package com.example.myapplication.presentation.stateMachine

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.domain.model.StandardError
import com.example.myapplication.presentation.widgets.ErrorView
import com.example.myapplication.presentation.widgets.SuperFrameLayout

class FullPageViewStateHandler(
    private val superFrameLayout: SuperFrameLayout,
    private val mainLayout: View
) {

    private var errorViewAction: ErrorView.ErrorViewAction? = null

    fun setStateMachine(
        outcome: MutableLiveData<FullPageState>,
        lifecycleOwner: LifecycleOwner,
        errorViewAction: ErrorView.ErrorViewAction? = null
    ) {
        this.errorViewAction = errorViewAction
        outcome.observe(lifecycleOwner, Observer {
            when (it) {
                is FullPageState.Loading ->
                    showLoading("")
                is FullPageState.Success -> showSuccess()
                is FullPageState.Error -> showError(it.error)
            }
        })
    }

    private fun showEmptyState() {
        superFrameLayout.setProgressBarVisibility(View.GONE)
        superFrameLayout.setErrorViewVisibility(View.GONE)
        mainLayout.visibility = View.GONE
        superFrameLayout.setEmptyStateVisibility(View.VISIBLE)
    }

    fun setEmptyState(view: View) {
        superFrameLayout.addEmptyState(view)
    }

    private fun showError(error: StandardError) {
        superFrameLayout.setProgressBarVisibility(View.GONE)
        superFrameLayout.setErrorViewVisibility(View.VISIBLE)
        superFrameLayout.setEmptyStateVisibility(View.GONE)
        mainLayout.visibility = View.GONE
        superFrameLayout.setError(error, errorViewAction)
    }

    private fun showSuccess() {
        superFrameLayout.setProgressBarVisibility(View.GONE)
        superFrameLayout.setErrorViewVisibility(View.GONE)
        superFrameLayout.setEmptyStateVisibility(View.GONE)
        mainLayout.visibility = View.VISIBLE
    }

    private fun showLoading(loaderText: String?) {
        superFrameLayout.setProgressBarText(loaderText)
        superFrameLayout.setProgressBarVisibility(View.VISIBLE)
        superFrameLayout.setErrorViewVisibility(View.GONE)
        superFrameLayout.setEmptyStateVisibility(View.GONE)
        mainLayout.visibility = View.GONE
    }

    private fun showLoading(loaderText: Int?) {
        superFrameLayout.setProgressBarText(loaderText)
        superFrameLayout.setProgressBarVisibility(View.VISIBLE)
        superFrameLayout.setErrorViewVisibility(View.GONE)
        mainLayout.visibility = View.GONE
    }
}