package com.example.bmo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bmo.R
import com.example.bmo.others.format
import com.example.bmo.pojo.News

class AllNewsAdapter(val view_type: Int, var items: ArrayList<News>, val on_item_click: OnItemClick): RecyclerView.Adapter<AllNewsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val news_container: CardView = itemView.findViewById(R.id.news_container)
        val news_image: ImageView = itemView.findViewById(R.id.news_image)
        val news_description: TextView = itemView.findViewById(R.id.news_desctiption)
        val news_author: TextView = itemView.findViewById(R.id.news_author)
        val news_published_at: TextView = itemView.findViewById(R.id.news_published_at)
        val news_favorite: ImageView = itemView.findViewById(R.id.news_favorite)
        val news_favorite_count: TextView = itemView.findViewById(R.id.news_favorite_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(
            when(view_type){
                0 -> R.layout.recycler_item_latest_news
                else-> R.layout.recycler_item_all_news }
            , parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.apply {
            val context = news_image.context

            items[position].apply {
                Glide.with(context).load(
                    when (urlToImage.isNullOrBlank()) {
                        true -> context.getDrawable(R.drawable.image_news_default)
                        else -> urlToImage
                    }
                ).into(news_image)
                news_description.text = description
                news_author.text = author
                news_published_at.text = publishedAt.format()
                news_favorite_count.text = "${favorite_count.format()}"

                news_favorite.setImageDrawable(
                    context.getDrawable(
                        when (is_favorite){
                            true -> R.drawable.ic_favorite_active
                            else -> R.drawable.ic_favorite_inactive
                        }
                    )
                )

                news_favorite.setOnClickListener {
                    favorite_count += if (is_favorite) -1 else 1

                    is_favorite = !is_favorite
                    on_item_click.on_favorite_click(position)

                    notifyDataSetChanged()
                }

                news_container.setOnClickListener { on_item_click.on_article_click(position) }
            }
        }
    }

    fun set_items(news: ArrayList<News>) {
        items = news
        notifyDataSetChanged()
    }

    fun item_at(position: Int) = items[position]

    override fun getItemCount() = items.size
}