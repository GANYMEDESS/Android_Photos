package com.bae.photoproject.application

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.bae.photoproject.BuildConfig.DEBUG
import com.bae.photoproject.model.database.FavoritePhotoRepository
import com.bae.photoproject.model.database.FavoritePhotoRoomDatabase

class JSApplication: Application()
{
    companion object{
        val debug = DEBUG
    }

    override fun onCreate() {
        super.onCreate()
        applicationContext?.let {
            if (DEBUG) {
                isDebuggable(it)
            }
        }
    }

    /**
     * @param context
     * @return debuggable
     */
    private fun isDebuggable(context: Context): Boolean {
        var debuggable = false

        try {
            val appInfo: ApplicationInfo = context.packageManager.getApplicationInfo(context.packageName, 0)
            debuggable = (0 != (appInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE))
        } catch (e: PackageManager.NameNotFoundException) {e.stackTrace}

        return debuggable
    }

    private val database by lazy { FavoritePhotoRoomDatabase.getDatabase(this@JSApplication) }
    val repository by lazy { FavoritePhotoRepository(database.favoritePhotoDao()) }
}