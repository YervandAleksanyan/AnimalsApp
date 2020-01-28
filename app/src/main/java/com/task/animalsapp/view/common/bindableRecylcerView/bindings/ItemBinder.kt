package com.task.animalsapp.view.common.bindableRecylcerView.bindings

import com.task.animalsapp.view.common.bindableRecylcerView.ViewHolder

interface ItemBinder<T> {
    fun getLayoutRes(model: T): Int
    fun getBindingVariable(model: T): Int
    fun resetViewHolder(viewHolder: ViewHolder) {

    }
}
