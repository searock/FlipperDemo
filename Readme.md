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

