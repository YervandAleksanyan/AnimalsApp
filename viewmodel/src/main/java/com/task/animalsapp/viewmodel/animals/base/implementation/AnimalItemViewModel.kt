package com.task.animalsapp.viewmodel.animals.base.implementation

import com.task.animalsapp.viewmodel.base.implementation.BaseSelectableViewModel

class AnimalItemViewModel(
    var id: String,
    val url: String,
    var title: String,
    selected: Boolean = false
) : BaseSelectableViewModel(selected)