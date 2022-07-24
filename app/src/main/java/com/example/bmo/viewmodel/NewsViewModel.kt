package com.example.bmo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bmo.pojo.News
import com.example.bmo.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val TAG = "NewsViewModel"
    private val composite_disposable = CompositeDisposable()

    private val _all_news_list: MutableLiveData<ArrayList<News>> = MutableLiveData(arrayListOf())
    val all_news_list: LiveData<ArrayList<News>> = _all_news_list

    private val _top_news_list: MutableLiveData<ArrayList<News>> = MutableLiveData(arrayListOf())
    val top_news_list: LiveData<ArrayList<News>> = _top_news_list

    private var _favorite_news_list: MutableLiveData<List<News>> = MutableLiveData(arrayListOf())
    val favorite_news_list: LiveData<List<News>> = _favorite_news_list

    fun all_news(
        api_key: String,
        q: String,
        domains: ArrayList<String>? = null,
        sort_by: String? = null,
        language: String? = null,
        from: String? = null,
        to: String? = null,
        page_size: Int? = null) {

        val observable =
            repository
                .get_all_news(api_key, q, domains, sort_by, language, from, to, page_size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ result -> _all_news_list.value = result.articles })
                { error -> Log.e(TAG,"VM all_news : $error") }

        composite_disposable.add(observable)
    }

    fun top_news(
        api_key: String,
        country: String,
        q: String? = null,
        category: String? = null,
        page_size: Int? = null) {

        val observable =
            repository
                .get_top_news(api_key, country, q, category, page_size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ result -> _top_news_list.value = result.articles })
                { error -> Log.e(TAG,"VM top_news: $error") }

        composite_disposable.add(observable)
    }

    fun insert_article(article: News) = repository.insert_article(article)
    fun delete_article(id: Int) = repository.delete_article(id)
    fun get_articles() { _favorite_news_list = repository.get_articles() }

    override fun onCleared() {
        super.onCleared()
        composite_disposable.clear()
    }

}
