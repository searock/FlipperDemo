package net.searock.fiddlerexample.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.searock.fiddlerexample.ui.MainActivity

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}