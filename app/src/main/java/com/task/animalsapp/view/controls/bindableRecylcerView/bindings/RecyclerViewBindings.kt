package com.task.animalsapp.view.controls.bindableRecylcerView.bindings

import androidx.databinding.BindingAdapter
import com.task.animalsapp.view.controls.bindableRecylcerView.BindableRecyclerView
import com.task.animalsapp.view.controls.bindableRecylcerView.eventHandlers.ClickHandler
import com.task.animalsapp.view.controls.bindableRecylcerView.eventHandlers.LongClickHandler

@BindingAdapter("items")
fun <T> setItems(
    recyclerView: BindableRecyclerView<T>,
    items: Collection<T>?
) {
    recyclerView.items = items
}


@BindingAdapter("clickHandler")
fun <T> setClickHandler(recyclerView: BindableRecyclerView<T>, handler: ClickHandler<T>) {
    recyclerView.clickHandler = handler
}


@BindingAdapter("longClickHandler")
fun <T> setlongClickHandler(
    recyclerView: BindableRecyclerView<T>,
    handler: LongClickHandler<T>
) {
    recyclerView.longClickHandler = handler
}

@BindingAdapter("itemBinder")
fun <T> setItemBinder(
    recyclerView: BindableRecyclerView<T>,
    itemViewMapper: ItemBinder<T>?
) {
    recyclerView.itemBinder = itemViewMapper
}