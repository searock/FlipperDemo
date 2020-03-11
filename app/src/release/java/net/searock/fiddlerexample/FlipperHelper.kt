package net.searock.fiddlerexample

import android.app.Application

class FlipperHelper {

    public fun init(application: Application){

    }

    fun getOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .build();
    }
}