package com.example.bmo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bmo.adapters.AllNewsAdapter
import com.example.bmo.adapters.OnItemClick
import com.example.bmo.databinding.FragmentAllNewsBinding
import com.example.bmo.others.*
import com.example.bmo.viewmodel.NewsViewModel

class AllNewsFragment : Fragment() {

    private val TAG = "AllNewsFragment"

    val TITLE = "World-wide"

    private var _binding : FragmentAllNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var view_model: NewsViewModel

    private lateinit var latest_news_adapter: AllNewsAdapter
    private lateinit var all_news_adapter: AllNewsAdapter

    private val current_date = current_date_time().format("yyyy-MM-dd")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val context = requireContext()
        val activity = requireActivity()

//        view_model = activity.let { ViewModelProvider(it)[NewsViewModel::class.java] }

        _binding = FragmentAllNewsBinding.inflate(inflater, container, false)
        binding.apply {

//            view_model.all_news(API_KEY, q = "a")

            latest_news_adapter =
                AllNewsAdapter(
                    0,
                    arrayListOf(),
                    object: OnItemClick {
                        override fun on_favorite_click(position: Int) {

                        }
                    })

            all_news_adapter =
                AllNewsAdapter(
                    1,
                    arrayListOf(),
                    object: OnItemClick {
                        override fun on_favorite_click(position: Int) {

                        }
                    })

//            view_model.all_news_list.observe(activity)
//            {
//                if (it.isNotEmpty()) {
//                    latest_news_adapter.set_items(it)
//
//                    latest_news_adapter.items.forEach { article->
//                        if (article.publishedAt.month() != current_date.split("-")[1] &&
//                                article.publishedAt.year() != current_date.split("-")[0])
//                            latest_news_adapter.items.remove(article)
//                    }
//                    Log.e(TAG, "latest news requested")
//                }
//            }
//
//            view_model.all_news_list.observe(activity)
//            {
//                if (it.isNotEmpty()) {
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