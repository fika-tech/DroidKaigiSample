package tech.fika.droidkaigi.android.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MonsterApplication : Application() {

    override fun onCreate() {
        super.onCreate()

//        if (BuildConfig.DEBUG) {
//            plant(Timber.DebugTree())
//        }
    }
}
