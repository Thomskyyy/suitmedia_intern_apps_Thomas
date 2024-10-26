package com.example.test_internship_thomaskevinsubowo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface retrofit {
    interface ApiService {
        @GET("users")
        fun getUsers(
            @Query("page") page: Int,
            @Query("per_page") perPage: Int
        ): Call<UserResponse>
    }
}