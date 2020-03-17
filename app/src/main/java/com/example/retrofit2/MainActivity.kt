package com.example.retrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit2.adapter.ArticleAdapter
import com.example.retrofit2.api.NewsApiInterface
import com.example.retrofit2.model.Article
import com.example.retrofit2.model.News
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()
    }

    fun loadArticleList(articleList: List<Article>) {
        recyclerNews.apply {
            layoutManager = LinearLayoutManager(context)
            val articleAdapter = ArticleAdapter(articleList, context)
            adapter = articleAdapter
        }
    }

    fun getNews() {
        var BASE_URL = "https://newsapi.org/v2/"

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var retrofitService = retrofit.create(NewsApiInterface::class.java)

        var apiCall = retrofitService.getNews("us","business","fa58fd035aeb4b14ab32c2cc20ece90c")

        apiCall.enqueue(object :
            Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.e("RETROFIT","response not successful");
            }
            override fun onResponse(
                call: Call<News>,
                response: Response<News>
            ){
                var articleList = response.body()?.articles
                Log.d("Response>>>>>>", articleList.toString())
                if (articleList != null) {
                    loadArticleList(articleList)
                }

            }
        })

    }
}
