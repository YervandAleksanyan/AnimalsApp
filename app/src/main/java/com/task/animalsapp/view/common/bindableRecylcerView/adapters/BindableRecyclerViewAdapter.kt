package com.task.animalsapp.view.common.bindableRecylcerView.adapters

import com.task.animalsapp.view.common.bindableRecylcerView.bindings.ItemBinder
import com.task.animalsapp.view.common.bindableRecylcerView.callbacks.OnListChangedCallback

class BindableRecyclerViewAdapter<T>(newItems: Collection<T>?, itemBinder: ItemBinder<T>) :
    BaseBindableRecyclerViewAdapter<T>(newItems, itemBinder) {
    override val itemModel: Int = -125
    override val onListChangedCallback: OnListChangedCallback<T> = OnListChangedCallback(this)

    //Init
    init {
        setItems(newItems)
    }
}