package com.github.repo

import android.app.Application
import com.github.repo.di.AppModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class GitApp: Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(AppModule.getModule()))
        Timber.plant(Timber.DebugTree())
    }
}
