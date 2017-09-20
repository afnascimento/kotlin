package br.com.anascimento.spotify.application

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex

/**
 * Created by andre.nascimento on 18/09/2017.
 */
class AppApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}