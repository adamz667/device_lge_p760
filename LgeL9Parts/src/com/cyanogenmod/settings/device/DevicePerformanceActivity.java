/*
 * Copyright (C) 2011 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cyanogenmod.settings.device;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import com.cyanogenmod.settings.device.R;

public class DevicePerformanceActivity extends PreferenceFragment {

    public static final String SHARED_PREFERENCES_BASENAME = "com.cyanogenmod.settings.device";
    public static final String ACTION_UPDATE_PREFERENCES = "com.cyanogenmod.settings.device.UPDATE";
    public static final String KEY_CPU_OVERCLOCK = "cpu_overclock";
    public static final String KEY_KSM_ENABLE = "ksm_enable";

    private ListPreference mCpuOverclock;
    private Preference ksmEnablePref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.performance);

        mCpuOverclock = (ListPreference) findPreference(KEY_CPU_OVERCLOCK);
        mCpuOverclock.setEnabled(CpuOverclock.isSupported());
        mCpuOverclock.setOnPreferenceChangeListener(new CpuOverclock());
        CpuOverclock.updateSummary(mCpuOverclock, Integer.parseInt(mCpuOverclock.getValue()));

	ksmEnablePref = findPreference(KEY_KSM_ENABLE);
 	ksmEnablePref.setEnabled(KsmEnable.isSupported());
	ksmEnablePref.setOnPreferenceChangeListener(new KsmEnable());
    }

}
