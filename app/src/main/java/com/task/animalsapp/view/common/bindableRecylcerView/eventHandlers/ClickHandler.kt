package com.task.animalsapp.view.common.bindableRecylcerView.eventHandlers

import android.view.View

interface ClickHandler<T> {
    fun onClick(viewModel: T?, view: View)
}