package net.searock.fiddlerexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_leak.*
import net.searock.fiddlerexample.FlipperApplication
import net.searock.fiddlerexample.R

class LeakActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leak)

        val app = application as FlipperApplication
        // What a Terrible Failure!
        app.leakedViews.add(textView)
    }
}
