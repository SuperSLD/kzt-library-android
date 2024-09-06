package com.example.data.config

import android.annotation.SuppressLint
import android.content.Context
import com.example.app_domain.datacontracts.config.ConfigKey
import com.example.app_domain.datacontracts.config.ConfigRepository

class ConfigPreferenceRepository(
    val context: Context,
) : ConfigRepository {

    @SuppressLint("CommitPrefEdits")
    override fun putValue(configKey: ConfigKey, value: String) {
        context
            .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(configKey.key, value)
            .apply()
    }

    override fun getValue(configKey: ConfigKey): String {
        return context
            .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getString(configKey.key, "")!!
    }
}

private const val PREF_NAME = "go_chill_prefs"