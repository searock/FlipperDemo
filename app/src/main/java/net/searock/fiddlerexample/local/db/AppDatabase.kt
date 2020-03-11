package net.searock.fiddlerexample.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import net.searock.fiddlerexample.domain.User

@Database(entities = [
    User::class
], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}