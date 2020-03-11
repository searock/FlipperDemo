package net.searock.fiddlerexample.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.searock.fiddlerexample.domain.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users limit 1")
    fun getUser(): List<User>

    @Query("DELETE FROM users")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)
}