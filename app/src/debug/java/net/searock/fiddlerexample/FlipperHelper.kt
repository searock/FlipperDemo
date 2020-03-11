package net.searock.fiddlerexample

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.core.FlipperClient
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary.LeakCanaryFlipperPlugin
import com.facebook.flipper.plugins.leakcanary.RecordLeakService
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import com.squareup.leakcanary.LeakCanary
import okhttp3.OkHttpClient


class FlipperHelper {

    fun init(application: Application){

        SoLoader.init(application, false);

        val client: FlipperClient = AndroidFlipperClient.getInstance(application)
        client.addPlugin(InspectorFlipperPlugin(application, DescriptorMapping.withDefaults()))
        client.addPlugin(FlipperHelper.networkPlugin)
        client.addPlugin(DatabasesFlipperPlugin(application))
        client.addPlugin(
            SharedPreferencesFlipperPlugin(application, "flipper")
        )
        client.addPlugin(LeakCanaryFlipperPlugin())
        client.addPlugin(CrashReporterPlugin.getInstance())
        client.start()

        val refWatcher = LeakCanary.refWatcher(application)
            .listenerServiceClass(RecordLeakService::class.java)
            .buildAndInstall()
    }

    fun getOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addNetworkInterceptor(FlipperOkhttpInterceptor(FlipperHelper.networkPlugin))
            .build();
    }

    object FlipperHelper{

        val networkPlugin = NetworkFlipperPlugin()
    }
}