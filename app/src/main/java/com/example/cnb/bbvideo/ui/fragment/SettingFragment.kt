package com.example.cnb.bbvideo.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceScreen
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cnb.bbvideo.R
import com.example.cnb.bbvideo.ui.activity.AboutActivity

/**
 * Created by cnb on 2017/11/28.
 */
class SettingFragment : PreferenceFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        addPreferencesFromResource(R.xml.setting)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onPreferenceTreeClick(preferenceScreen: PreferenceScreen?, preference: Preference?): Boolean {
        val key = preference?.key
        if ("about".equals(key)) {
            activity.startActivity(Intent(activity, AboutActivity::class.java))
        }

        return super.onPreferenceTreeClick(preferenceScreen, preference)
    }

}