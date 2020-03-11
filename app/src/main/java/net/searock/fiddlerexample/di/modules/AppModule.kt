package net.searock.fiddlerexample.di.modules

import android.app.Application
import androidx.room.Room
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import dagger.Module
import dagger.Provides
import net.searock.fiddlerexample.FlipperHelper
import net.searock.fiddlerexample.local.db.AppDatabase
import net.searock.fiddlerexample.local.db.UserDao
import net.searock.fiddlerexample.remote.GithubApi
import net.searock.fiddlerexample.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(FlipperHelper().getOkHttpClient())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesGithubApi(retrofit: Retrofit): GithubApi{

        return retrofit.create(GithubApi::class.java)
    }

    @Provides
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java, "users.db"
        ).build()
    }

    @Provides
    fun getDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }
}