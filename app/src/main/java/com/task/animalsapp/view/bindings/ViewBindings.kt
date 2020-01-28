package com.task.animalsapp.view.bindings

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.task.animalsapp.viewmodel.base.ICommand

@BindingAdapter(value = ["isBusy", "loadCommand", "reloadCommand"], requireAll = false)
fun setSwipeToRefreshIsBusy(
    swipeRefreshLayout: SwipeRefreshLayout,
    isBusy: LiveData<Boolean>,
    loadCommand: ICommand?,
    reloadCommand: ICommand?

) {
    swipeRefreshLayout.setOnRefreshListener {
        reloadCommand?.execute() ?: run {
            loadCommand?.execute()
        }
    }
    swipeRefreshLayout.isRefreshing = isBusy.value ?: true
}