package com.example.ignfootabbed

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.tab2_fragment.*
import okhttp3.*
import java.io.IOException

class VideoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var rootView = inflater.inflate(R.layout.tab2_fragment, container, false)
        val rv_fragArticle = rootView.findViewById(R.id.rv_frag2) as RecyclerView
        rv_fragArticle.layoutManager = LinearLayoutManager(activity)

        rv_fragArticle.addItemDecoration(DividerItemDecoration(getContext(), ClipDrawable.HORIZONTAL))

        fetchJson()
        return rootView
    }
    fun fetchJson(){

        val url = "https://ign-apis.herokuapp.com/content"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, HomeFeed::class.java)
                getActivity()?.runOnUiThread {
                    rv_frag2.adapter = MainAdapter(sortContentVideos(homeFeed))
                }
            }
            override fun onFailure(call: Call?, e: IOException) {
                println("Failed to execute")
            }
        })

    }

    fun sortContentVideos(homeFeed:HomeFeed):MutableList<content>{ //Creating list that will store all videos
        var videos : MutableList<content> = ArrayList()
        var dude = homeFeed.data.indices
        for(index in homeFeed.data.indices){
            if (homeFeed.data[index].contentType == "video") {
                println("yo this is the index videos $index")
                videos.add(homeFeed.data[index])
            }
        }
        return videos
    }
}