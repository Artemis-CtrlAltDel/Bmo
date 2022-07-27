package com.example.bmo.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bmo.R
import com.example.bmo.pojo.Filter

class NewsFilterAdapter(val items: ArrayList<Filter>, val on_item_click: OnItemClick): RecyclerView.Adapter<NewsFilterAdapter.ViewHolder>() {

    private lateinit var context: Context

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val filter_container: LinearLayout = itemView.findViewById(R.id.filter_container)
        val filter_category: TextView = itemView.findViewById(R.id.filter_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_filter_card, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.apply {
            context = filter_category.context

            items[position].apply {
                filter_category.text = category
                filter_container.backgroundTintList =
                    ColorStateList.valueOf(context.getColor(if (is_chosen) R.color.color_primary_3 else R.color.transparent))

                filter_container.setOnClickListener {
                    is_chosen = !is_chosen
                    items.filter { item -> item.category != this.category }.forEach { it.is_chosen = false }

                    on_item_click.on_article_click(position)
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun getItemCount() = items.size

    fun item_at(position: Int) = items[position]
}