package com.task.animalsapp.viewmodel.base.implementation

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.task.animalsapp.viewmodel.BR
import com.task.animalsapp.viewmodel.base.ISelectableViewModel

open class BaseSelectableViewModel(selected: Boolean = false) : BaseObservable(),
    ISelectableViewModel {

    override var isSelected = selected
        @Bindable get() = field
        @Bindable set(value) {
            field = value
            notifyPropertyChanged(BR.selected)
        }
}