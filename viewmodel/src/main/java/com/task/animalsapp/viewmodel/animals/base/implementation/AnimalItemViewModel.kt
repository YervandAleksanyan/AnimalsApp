package com.task.animalsapp.viewmodel.animals.base.implementation

import android.os.Parcelable
import com.task.animalsapp.viewmodel.base.implementation.BaseSelectableViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class AnimalItemViewModel(
    var id: String,
    val url: String,
    var title: String,
    val selected: Boolean = false
) : BaseSelectableViewModel(selected), Parcelable