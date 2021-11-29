package jth.companies.jth

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class Application : MultiDexApplication() {
    override fun attachBaseContext(context: Context?) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
    }
}