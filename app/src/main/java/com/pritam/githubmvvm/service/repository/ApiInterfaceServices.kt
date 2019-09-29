package com.pritam.githubmvvm.service.repository

import com.pritam.githubmvvm.service.model.UserDetailsResponse
import com.pritam.githubmvvm.service.model.UserFollowResponse
import com.pritam.githubmvvm.service.model.UserReposResponse
import com.pritam.githubmvvm.service.model.FactResponse
import com.pritam.githubmvvm.service.model.UserSerachResponse
import com.pritam.githubmvvm.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterfaceServices {
    @GET(Constants.FACT_URL)
    fun getFactList(): Call<FactResponse>

    // https://api.github.com/search/users?q=pritamkhose&page=1
    @GET("search/users")
    fun getUserSerachList(
        @Query("q") username: String,
        @Query("page") pageno: Int
    ): Call<UserSerachResponse>

    // https://api.github.com/users/pritamkhose
    @GET("users/{username}")
    fun getUserDetails(@Path("username") username: String): Call<UserDetailsResponse>

    //https://api.github.com/users/pritamkhose/repos?sort=updated&per_page=25
    @GET("users/{username}/repos")
    fun getUserRepos(
        @Path("username") username: String,
        @Query("sort") sort: String,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int
    ): Call<ArrayList<UserReposResponse>>

    // https://api.github.com/users/pritamkhose/followers
    @GET("users/{username}/followers")
    fun getUserFollowers(@Path("username") username: String): Call<List<UserFollowResponse>>

    // https://api.github.com/users/pritamkhose/following
    @GET("users/{username}/following")
    fun getUserFollowing(@Path("username") username: String): Call<List<UserFollowResponse>>

    // https://api.github.com/search/repositories?q=topic:android
    @GET("search/repositories")
    fun getUserSearchDefault(@Query("q") query: String): Call<UserDetailsResponse>

}