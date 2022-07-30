package com.example.bmo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.bmo.databinding.ActivityNewsCardBinding
import com.example.bmo.others.favorite_item
import com.example.bmo.others.format
import com.example.bmo.others.is_source_available
import com.example.bmo.pojo.News
import com.example.bmo.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsCardActivity : AppCompatActivity() {

    private val TAG = "NewsCardActivity"

    private lateinit var view_model: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var intent = intent

        val binding = ActivityNewsCardBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)

            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
            )

            view_model = ViewModelProvider(this@NewsCardActivity)[NewsViewModel::class.java]

            val article = intent.getParcelableExtra<News>("article")

            Log.e(TAG, "news card activity article : ${article}")

            article?.apply {

                if (!this.is_source_available()) this.source.id = ""

                if (title.isNullOrEmpty()) title = "Title is not available"
                if (publishedAt.isNullOrEmpty()) publishedAt = "Not available"
                if (author.isNullOrEmpty()) author = "Not available"
                if (description.isNullOrEmpty()) description = "Description is not available"
                if (content.isNullOrEmpty()) content = "Content is not available"
                if (url.isNullOrEmpty()) {
                    url = "Link is not available"
                    newsReadmore.isEnabled = false
                }

                Glide.with(applicationContext).load(urlToImage).into(newsImage)
                newsTitle.text = title
                newsPublishedAt.text = publishedAt!!.format()
                newsAuthor.text = author
                newsFavoriteCount.text = favorite_count.format()
                newsDescription.text = description
                newsContent.text = content

                newsReadmore.text = url
                newsReadmore.setTextColor(getColor(R.color.color_link))
                newsReadmore.setOnClickListener {
                    intent = Intent("android.intent.action.VIEW", Uri.parse(url))
                    startActivity(intent)
                }

                icFavorite.setOnClickListener {
                    article.favorite_item(view_model)

                    (it as ImageView).setImageDrawable(
                        getDrawable(
                            when(is_favorite){
                                true -> R.drawable.ic_favorite_active
                                else -> R.drawable.ic_favorite_inactive
                            }
                        )
                    )
                }
            }

            icGoBack.setOnClickListener {
                intent = Intent(this@NewsCardActivity, MainActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }

        }

    }
}