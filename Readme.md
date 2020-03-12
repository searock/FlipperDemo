# Flipper Demo

This is a demo app for demonstrating how to use Flipper. This code does not use best practices and  is written only to demonstrate the usage of Flipper.


# Flipper

Flipper is a platform for debugging mobile apps on iOS and Android. Visualize, inspect, and control your apps from a simple desktop interface. Use Flipper as is or extend it using the plugin API. You can [download](https://fbflipper.com/) it from their website.

## Dependencies

The Flipper dependency

    debugImplementation 'com.facebook.flipper:flipper:0.33.1'  
    debugImplementation 'com.facebook.soloader:soloader:0.8.2'  
    releaseImplementation 'com.facebook.flipper:flipper-noop:0.27.0'  
    debugImplementation 'com.facebook.flipper:flipper-network-plugin:0.27.0'

The Leakcanary  dependency

    debugImplementation 'com.facebook.flipper:flipper-leakcanary-plugin:0.33.1'  
    debugImplementation 'com.squareup.leakcanary:leakcanary-android:1.6.3'  
    releaseImplementation 'com.squareup.leakcanary:leakcanary-android-no-op:1.6.1'


## Code
1. Create an Application class. 
2. Create 2 helper class for flipper one for debug where it configures flipper and another for release where it does nothing. 


## Basic Setup

Comes with layout inspector

    SoLoader.init(application, false);  
      
    val client: FlipperClient = AndroidFlipperClient.getInstance(application)  
    client.addPlugin(InspectorFlipperPlugin(application, DescriptorMapping.withDefaults()))
    client.start()

![Layout Inspector](https://imgur.com/download/2PwYlcg/)

## With Network Inspection

You need to share the NetworkFlipperPlugin object with the AndroidFlipperClient and FlipperOkhttpInterceptor object.

    object FlipperHelper{  
      
        val networkPlugin = NetworkFlipperPlugin()  
    }

Add the interceptor in okhttp

    fun getOkHttpClient(): OkHttpClient{  
        return OkHttpClient.Builder()  
            .addNetworkInterceptor(FlipperOkhttpInterceptor(FlipperHelper.networkPlugin))  
            .build();  
    }

Initialize flipper

    SoLoader.init(application, false);  
      
    val client: FlipperClient = AndroidFlipperClient.getInstance(application)  
    client.addPlugin(InspectorFlipperPlugin(application, DescriptorMapping.withDefaults()))  
    client.addPlugin(FlipperHelper.networkPlugin)
    client.start()

![Network Inspector](https://imgur.com/download/lmnIttb/)

## With Database Inspector

    SoLoader.init(application, false);  
      
    val client: FlipperClient = AndroidFlipperClient.getInstance(application)  
    client.addPlugin(InspectorFlipperPlugin(application, DescriptorMapping.withDefaults()))
    client.addPlugin(DatabasesFlipperPlugin(application))
    client.start()

![Database Inspector](https://imgur.com/download/kuCpuRP/)

## With SharedPreference Inspector

    SoLoader.init(application, false);  
      
    val client: FlipperClient = AndroidFlipperClient.getInstance(application)  
    client.addPlugin(InspectorFlipperPlugin(application, DescriptorMapping.withDefaults()))
    client.addPlugin(  
        SharedPreferencesFlipperPlugin(application, "flipper")  
    )
    client.start()

![Shared Preferences](https://imgur.com/download/LPJBrRm/)

## With CrashReporter
You can use this plugin to notify you of exceptions that you suppress.

    SoLoader.init(application, false);  
      
    val client: FlipperClient = AndroidFlipperClient.getInstance(application)  
    client.addPlugin(InspectorFlipperPlugin(application, DescriptorMapping.withDefaults()))
    client.addPlugin(CrashReporterPlugin.getInstance())  
    client.start()

To report an exception use

    CrashReporterPlugin.getInstance()  
        .sendExceptionMessage(Thread.currentThread(), Exception("Hello World!"))

![Crash Reporter](https://imgur.com/download/UwmzX6q/)

## With LeakCanary

    SoLoader.init(application, false);  
      
    val client: FlipperClient = AndroidFlipperClient.getInstance(application)  
    client.addPlugin(InspectorFlipperPlugin(application, DescriptorMapping.withDefaults()))
    client.addPlugin(LeakCanaryFlipperPlugin())
    client.start()
    val refWatcher = LeakCanary.refWatcher(application)  
        .listenerServiceClass(RecordLeakService::class.java)  
        .buildAndInstall()

In your debug manifest add RecordLeakService service.

    <service android:name="com.facebook.flipper.plugins.leakcanary.RecordLeakService" />

![LeakCanary](https://imgur.com/download/MYc336C)
