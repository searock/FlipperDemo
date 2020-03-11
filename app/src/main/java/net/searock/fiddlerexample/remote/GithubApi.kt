package net.searock.fiddlerexample.remote

import net.searock.fiddlerexample.domain.User
import retrofit2.http.GET

interface GithubApi {

    @GET("users/searock")
    suspend fun getUser(): User
}