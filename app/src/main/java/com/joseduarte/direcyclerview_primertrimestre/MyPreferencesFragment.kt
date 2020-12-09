package com.joseduarte.direcyclerview_primertrimestre

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class MyPreferencesFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref_settings, rootKey)
    }
}