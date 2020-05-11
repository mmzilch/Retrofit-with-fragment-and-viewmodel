package com.example.retrofitwithfragment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitwithfragment.R
import com.example.retrofitwithfragment.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter (var articleList:List<Article> = ArrayList())
    :RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>()
{
    inner class ArticleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        fun bindNews(article :Article)
        {
            Picasso.get().load(article.urlToImage)
                .placeholder(R.drawable.ic_launcher_background).into(itemView.imageNews)//place holder is to show temp image when loading
            itemView.tvTitle.text=article.title
            itemView.tvContent.text=article.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        return ArticleViewHolder(view)
    }


    override fun getItemCount(): Int {
        Log.d("new>>>>>",articleList.size.toString())

        return articleList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindNews(articleList[position])
    }

    fun updateList(article: List<Article>)
    {
        this.articleList = article
        notifyDataSetChanged()
    }
}