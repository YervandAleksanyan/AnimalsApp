package com.task.animalsapp.managers

import android.content.Context
import android.content.SharedPreferences
import com.task.animalsapp.core.managers.IPreferencesManager
import com.task.animalsapp.view.utils.get
import com.task.animalsapp.view.utils.set

class PreferencesManager(context: Context) : IPreferencesManager {

    companion object {
        private const val CAT_KEY = "cat"
        private const val DOG_KEY = "dog"
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(context.packageName + ":Preferences", Context.MODE_PRIVATE)

    override var selectedCatId: Int
        get() = preferences.get(CAT_KEY, -1)
        set(value) {
            preferences.set(CAT_KEY, value)
        }

    override var selectedDogId: Int
        get() = preferences.get(DOG_KEY, -1)
        set(value) {
            preferences.set(DOG_KEY, value)
        }

}