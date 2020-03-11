package net.searock.fiddlerexample.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import net.searock.fiddlerexample.R
import net.searock.fiddlerexample.domain.User
import net.searock.fiddlerexample.local.db.UserDao
import net.searock.fiddlerexample.remote.GithubApi
import java.lang.Exception
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var githubApi: GithubApi

    @Inject
    lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNetwork.setOnClickListener {

            runBlocking {
                val user = githubApi.getUser().name
                Toast.makeText(this@MainActivity.applicationContext, user!!, Toast.LENGTH_LONG).show()
            }
        }

        btnDatabase.setOnClickListener {

            GlobalScope.launch {

                userDao.deleteAll()
                userDao.insert(User(1, "Searock", "http://"))
                val user = userDao.getUser()[0]

                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity.applicationContext, user.name!!, Toast.LENGTH_LONG).show()
                }
            }
        }

        btnSharedPreference.setOnClickListener {

            val sharedPref = this.getSharedPreferences("flipper", Context.MODE_PRIVATE)
            with (sharedPref.edit()) {
                putString("user", "Searock")
                commit()
            }

            val userName = sharedPref.getString("user", "")
            Toast.makeText(this@MainActivity.applicationContext, userName, Toast.LENGTH_LONG).show()


        }

        btnLeakActivity.setOnClickListener {
            val intent = Intent(this, LeakActivity::class.java)
            startActivity(intent)
        }

        btnCrashReporter.setOnClickListener {
            CrashReporterPlugin.getInstance()
                .sendExceptionMessage(Thread.currentThread(), Exception("Hello World!"))
        }

    }
}
