package net.searock.fiddlerexample

import android.view.View
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import net.searock.fiddlerexample.di.components.DaggerAppComponent

class FlipperApplication: DaggerApplication() {

    val leakedViews = mutableListOf<View>()

    override fun onCreate() {
        super.onCreate()

        FlipperHelper().init(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}