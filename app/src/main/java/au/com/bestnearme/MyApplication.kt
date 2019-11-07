package au.com.bestnearme

import android.app.Application
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
}