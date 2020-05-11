package com.example.retrofitwithfragment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitwithfragment.api.NewsApi
import com.example.retrofitwithfragment.api.NewsApiInterface
import com.example.retrofitwithfragment.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    var results: MutableLiveData<News> = MutableLiveData()

    fun getResults(): LiveData<News> = results//to get data
    //==return results

    private val newsApi: NewsApi = NewsApi()//obj//news api => getNews()

    fun loadResult(category: String) {
        val apiCall = newsApi.getNews(category)
        apiCall.enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                response.isSuccessful.let {
                    //val  resultList = response.body()?.articles//cause of Call<News> and article can be null or not
                    val resultList = News(
                        response.body()?.articles ?: emptyList()
                        //elvis operator == ternary operator (if article is null it will return empty list and article is not null it return article)
                        //to avoid NullPointerException
                    )
                    Log.d("resultlist>>>",resultList.toString())
                    results.value = resultList

                }
            }

        })
    }
}