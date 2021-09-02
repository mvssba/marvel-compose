package br.com.marcos2silva.marvel_compose

import android.app.Application
import br.com.marcos2silva.marvel_compose.di.MarvelModule
import br.com.marcos2silva.network.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MarvelApplication)

            MarvelModule.load()
            NetworkModule.load()
        }
    }
}