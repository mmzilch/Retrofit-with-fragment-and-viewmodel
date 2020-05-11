package com.example.retrofitwithfragment.api

import com.example.retrofitwithfragment.model.News
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApi {
    private val newsApiInterface:NewsApiInterface

    companion object {
        const val Base_URL="http://newsapi.org/v2/"//can call with direct class name
    }

    init {//when create class obj and it create retrofits
        val retrofit = Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsApiInterface=retrofit.create(NewsApiInterface::class.java)
    }

    fun getNews(category:String): Call<News>
    {
        return newsApiInterface.getNews(category)
    }

}