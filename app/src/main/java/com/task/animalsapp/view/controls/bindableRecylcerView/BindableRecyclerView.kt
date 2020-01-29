package com.task.animalsapp.view.controls.bindableRecylcerView

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.task.animalsapp.view.controls.bindableRecylcerView.adapters.BaseBindableRecyclerViewAdapter
import com.task.animalsapp.view.controls.bindableRecylcerView.adapters.BindableRecyclerViewAdapter
import com.task.animalsapp.view.controls.bindableRecylcerView.bindings.ItemBinder
import com.task.animalsapp.view.controls.bindableRecylcerView.eventHandlers.ClickHandler
import com.task.animalsapp.view.controls.bindableRecylcerView.eventHandlers.LongClickHandler

open class BindableRecyclerView<T> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var adapter: BaseBindableRecyclerViewAdapter<T>? = null
        set(value) {
            field = value
            setAdapter(value)
        }

    open var items: Collection<T>? = null
        set(value) {
            field = value
            adapter?.setItems(items)
        }

    var clickHandler: ClickHandler<T>? = null
        set(value) {
            field = value
            adapter?.let {
                it.clickHandler = value
            }
        }

    var longClickHandler: LongClickHandler<T>? = null
        set(value) {
            field = value
            adapter?.let {
                it.longClickHandler = value
            }
        }

    open var itemBinder: ItemBinder<T>? = null
        set(value) {
            if (field != value) {
                field = value
                if (field == null) {
                    adapter = null

                } else {
                    adapter = BindableRecyclerViewAdapter(items, field!!)
                    adapter!!.clickHandler = clickHandler
                    adapter!!.longClickHandler = longClickHandler
                }
            }
        }
}