package com.example.ignfootabbed

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.tab1_fragment.*
import okhttp3.*
import java.io.IOException

class ArticleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.tab1_fragment, container, false)
        val rv_fragArticle = rootView.findViewById(R.id.rv_frag1) as RecyclerView
        rv_fragArticle.layoutManager = LinearLayoutManager(activity)

        rv_fragArticle.addItemDecoration(DividerItemDecoration(getContext(), HORIZONTAL))

        fetchJson()

        return rootView
    }

    fun fetchJson(){

        val url = "https://ign-apis.herokuapp.com/articles"
        val request = Request.Builder().url(url).build()
        Log.d("LOGGING",request.toString());
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, HomeFeed::class.java)
                Log.d("LOGGING",homeFeed.toString());
                getActivity()?.runOnUiThread {
                    rv_frag1.adapter = MainAdapter(sortContentArticle(homeFeed))
                }
            }
            override fun onFailure(call: Call?, e: IOException) {
                println("Failed to execute")
                Log.d("LOGGING","FAILED");
            }
        })

    }

    fun sortContentArticle(homeFeed:HomeFeed):MutableList<content>{ //Creating list that will store all Articles
        var articles : MutableList<content> = mutableListOf<content>()
        var dude = homeFeed.data.indices
        for(index in homeFeed.data.indices){
            if (homeFeed.data[index].contentType == "article") {
                println("yo this is the index $index")
                articles.add(homeFeed.data[index])
            }
        }
        return articles
    }



}

