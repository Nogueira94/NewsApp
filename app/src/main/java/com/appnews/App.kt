package com.appnews

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin{
            androidContext(this@App)
            modules(listOf(appNewsModule, secModule))
        }
    }
    

    companion object{
        var ALREADY_AUTH = false
    }
}