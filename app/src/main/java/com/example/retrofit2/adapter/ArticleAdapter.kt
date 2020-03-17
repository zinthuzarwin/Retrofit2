package com.example.retrofit2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit2.R
import com.example.retrofit2.model.Article
import com.example.retrofit2.model.News
import kotlinx.android.synthetic.main.item_news.view.*

class ArticleAdapter(var articleList: List<Article>, context: Context) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

         fun  bindArticle(article : Article) {

             itemView.txtTitle.text =  article.title
             itemView.txtContent.text = article.content
         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapter.ArticleViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ArticleAdapter.ArticleViewHolder, position: Int) {
        holder.bindArticle(articleList[position])
    }

}