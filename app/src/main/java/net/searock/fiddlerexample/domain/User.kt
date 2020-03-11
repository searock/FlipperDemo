package net.searock.fiddlerexample.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = false)
    var id: Int?,
    var name: String?,
    var avatar_url: String?
)