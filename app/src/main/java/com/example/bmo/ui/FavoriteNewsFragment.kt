package com.example.bmo.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bmo.NewsCardActivity
import com.example.bmo.adapters.AllNewsAdapter
import com.example.bmo.adapters.OnItemClick
import com.example.bmo.databinding.FragmentFavoriteNewsBinding
import com.example.bmo.others.favorite_item
import com.example.bmo.pojo.News
import com.example.bmo.viewmodel.NewsViewModel

class FavoriteNewsFragment : Fragment() {

    private val TAG = "FavoriteNewsFragment"

    val TITLE = "Favorites"

    private var _binding : FragmentFavoriteNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var view_model: NewsViewModel
    private lateinit var favorite_news_adapter: AllNewsAdapter

    private lateinit var intent: Intent
    private lateinit var article: News

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val context = requireContext()
        val activity = requireActivity()

        view_model = activity.let { ViewModelProvider(it)[NewsViewModel::class.java] }

        intent = Intent(activity, NewsCardActivity::class.java)

        _binding = FragmentFavoriteNewsBinding.inflate(inflater, container, false)
        binding.apply {

            favorite_news_adapter = AllNewsAdapter(
                1,
                arrayListOf(),
                object: OnItemClick {
                    override fun on_favorite_click(position: Int) {
                        favorite_news_adapter.item_at(position).favorite_item(view_model)
                    }

                    override fun on_article_click(position: Int) {
                        article = favorite_news_adapter.item_at(position)
                        intent.putExtra("article", article)
                        startActivity(intent)
                    }
                }
            )

            view_model.get_articles()
            view_model.favorite_news_list?.observe(activity)
            {
                if (it.isNotEmpty()){
                    favorite_news_adapter.set_items(it as ArrayList<News>)
                }
            }

            favoriteNewsRecycler.adapter = favorite_news_adapter
            favoriteNewsRecycler.setHasFixedSize(true)
            favoriteNewsRecycler.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)

            return root
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}