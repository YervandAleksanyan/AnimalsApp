package com.task.animalsapp.view.bindings

import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.squareup.picasso.Picasso
import com.task.animalsapp.R
import com.task.animalsapp.view.utils.CircleTransform
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

@BindingAdapter(value = ["imageUrl"])
fun loadImage(view: ImageView, imageUrl: String) {
    Picasso.get()
        .load(imageUrl)
        .transform(CircleTransform())
        .into(view)
}

@BindingAdapter("isSelected")
fun isSelected(view: CardView, isSelected: Boolean) {
    if (isSelected) {
        view.setCardBackgroundColor(
            ContextCompat.getColor(
                view.context,
                R.color.selected_animal_cell_background_color
            )
        )
    } else {
        view.setCardBackgroundColor(
            ContextCompat.getColor(
                view.context,
                R.color.color_three_opacity
            )
        )
    }
}