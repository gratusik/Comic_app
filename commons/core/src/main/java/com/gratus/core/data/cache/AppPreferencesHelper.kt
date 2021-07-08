package com.gratus.core.data.cache

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.gratus.core.util.CoreConstants.PrefConstant.APP_PREF_NAME
import com.gratus.core.util.CoreConstants.PrefConstant.LAST_SEEN_COMIC_ID
import com.gratus.core.util.CoreConstants.PrefConstant.LATEST_COMIC_ID
import javax.inject.Inject

// Shared Preferences
class AppPreferencesHelper @Inject constructor(context: Context) : AppPrefHelper {
    private var gson = Gson()
    private var mPrefs: SharedPreferences =
        context.getSharedPreferences(APP_PREF_NAME, Context.MODE_PRIVATE)

    fun isClear(): Boolean {
        return false
    }

    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    fun setClear(clear: Boolean) {
        val editor = mPrefs.edit()
        editor.clear()
        editor.apply()
    }

    // to get last seen comic id
    override fun getLastSeenComicId(): Int {
        return mPrefs.getInt(LAST_SEEN_COMIC_ID, 0)
    }

    // to set last seen comic id 
    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    override fun setLastSeenComicId(lastSeenComicId: Int) {
        mPrefs.edit().putInt(LAST_SEEN_COMIC_ID, lastSeenComicId).apply()
    }

    // to get latest comic id
    override fun getLatestComicId(): Int {
        return mPrefs.getInt(LATEST_COMIC_ID, 0)
    }

    // to set latest comic id
    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    override fun setLatestComicId(latestComicId: Int) {
        mPrefs.edit().putInt(LATEST_COMIC_ID, latestComicId).apply()
    }
}
