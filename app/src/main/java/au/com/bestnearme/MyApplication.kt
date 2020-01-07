package au.com.bestnearme

import android.app.Application
import android.content.Context
import android.content.res.Resources
import au.com.bestnearme.component.ApplicationComponent
import au.com.bestnearme.component.DaggerApplicationComponent
import au.com.bestnearme.di.module.NetModule

class MyApplication : Application() {

    lateinit var applicationComponent : ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .netModule(NetModule())
            .build()

        applicationComponent.inject(this)
    }

    companion object {
        private var instance: MyApplication? = null

        fun getContext(): Context {
            return instance?.getApplicationContext()!!
        }
    }
}