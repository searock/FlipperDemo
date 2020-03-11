package net.searock.fiddlerexample.di.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import net.searock.fiddlerexample.FlipperApplication
import net.searock.fiddlerexample.di.modules.ActivityBuildersModule
import net.searock.fiddlerexample.di.modules.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class,
    AppModule::class
])
interface AppComponent: AndroidInjector<FlipperApplication> {


    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}