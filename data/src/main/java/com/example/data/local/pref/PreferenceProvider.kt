package com.example.data.local.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PreferenceProvider
@Inject
constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        const val IS_LOGGED_IN = "IS_LOGGED_IN"
    }

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(context)


    fun clearPreferences() {
        preference.edit().clear().apply()
    }


    fun updateIsLoggedIn(value: Boolean) {
        preference.edit().putBoolean(
            IS_LOGGED_IN,
            value
        ).apply()
    }

    fun getIsLoggedIn(): Boolean {
        return preference.getBoolean(IS_LOGGED_IN, false)
    }

}