package com.example.bmo.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bmo.NewsCardActivity
import com.example.bmo.adapters.AllNewsAdapter
import com.example.bmo.adapters.OnItemClick
import com.example.bmo.databinding.FragmentAllNewsBinding
import com.example.bmo.others.*
import com.example.bmo.pojo.News
import com.example.bmo.pojo.Source
import com.example.bmo.viewmodel.NewsViewModel

class AllNewsFragment : Fragment() {

    private val TAG = "AllNewsFragment"

    val TITLE = "World-wide"

    private var _binding : FragmentAllNewsBinding? = null
    private val binding get() = _binding!!

//    private lateinit var view_model: NewsViewModel

    private lateinit var latest_news_adapter: AllNewsAdapter
    private lateinit var all_news_adapter: AllNewsAdapter

    private val current_date = current_date_time().format("yyyy-MM-dd")

    private lateinit var intent: Intent
    private lateinit var article: News

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         //Inflate the layout for this fragment

        val context = requireContext()
        val activity = requireActivity()

        intent = Intent(activity, NewsCardActivity::class.java)
//        view_model = activity.let { ViewModelProvider(it)[NewsViewModel::class.java] }

        _binding = FragmentAllNewsBinding.inflate(inflater, container, false)
        binding.apply {

//            view_model.all_news(API_KEY, q = "a", sort_by = "publishedAt")

            latest_news_adapter =
                AllNewsAdapter(
                    0,
                    arrayListOf(),
                    object: OnItemClick {
                        override fun on_favorite_click(position: Int) {
//                            latest_news_adapter.item_at(position).favorite_item(view_model = view_model)
                        }

                        override fun on_article_click(position: Int) {
                            article = latest_news_adapter.item_at(position)
                            when (article.is_source_available()) {
                                true -> {intent.putExtra("article", article)
                                        startActivity(intent)}
                                else ->  Toast.makeText(context, "Source id is missing", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })

            all_news_adapter =
                AllNewsAdapter(
                    1,
                    arrayListOf(),
                    object: OnItemClick {
                        override fun on_favorite_click(position: Int) {
//                            all_news_adapter.item_at(position).favorite_item(view_model = view_model)
                        }

                        override fun on_article_click(position: Int) {
                            article = all_news_adapter.item_at(position)
                            when (article.is_source_available()) {
                                true -> {intent.putExtra("article", article)
                                        startActivity(intent)}
                                else ->  Toast.makeText(context, "Source id is missing", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })

            // Init adapters state
            all_news_adapter.set_items(arrayListOf(
                News(0, Source("", ""),
                    description= "", content= "", url= "")
            ))
            latest_news_adapter.set_items(arrayListOf(
                News(0, Source("", ""),
                    description= "", content= "", url= "")
            ))

//            view_model.all_news_list.observe(activity)
//            {
//                if (it.isNotEmpty()) {
//                    (it as List<News>).filter { it.description.isNotEmpty() || it.urlToImage.isNotEmpty() }
//
//                    latest_news_adapter.set_items(it)
//                    Log.e(TAG, "latest news requested")
//                }
//            }

//            view_model.all_news_list.observe(activity)
//            {
//                if (it.isNotEmpty()) {
//                    (it as List<News>).filter { it.description.isNotEmpty() || it.urlToImage.isNotEmpty() }
//
//                    all_news_adapter.set_items(it)
//                    Log.e(TAG, "all news requested")
//                }
//            }

            latestNewsRecycler.adapter = latest_news_adapter
            latestNewsRecycler.setHasFixedSize(true)
            latestNewsRecycler.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            allNewsRecycler.adapter = all_news_adapter
            allNewsRecycler.setHasFixedSize(true)
            allNewsRecycler.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            return root
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}