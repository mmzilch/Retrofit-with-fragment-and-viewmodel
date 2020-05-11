package com.example.retrofitwithfragment.api

import com.example.retrofitwithfragment.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApiInterface {

//write with http header

    @Headers("X-Api-Key: 8f39db3aa4ef40ea83d8ff29a4794ef4")
    @GET("top-headlines")
    fun getNews(
        @Query("category")
        category: String
    ): Call<News>


}