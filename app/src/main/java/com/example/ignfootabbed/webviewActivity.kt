package com.example.ignfootabbed


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.webview_layout.*

import com.example.ignfootabbed.CustomeViewHolder.Companion.CONTENT_TYPE
import com.example.ignfootabbed.CustomeViewHolder.Companion.POSITION_OF_CLICKED
import com.example.ignfootabbed.CustomeViewHolder.Companion.WEBLINK_DATE
import com.example.ignfootabbed.CustomeViewHolder.Companion.WEBLINK_SLUG

class webViewActivity(): AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview_layout)
        val pos = intent.getIntExtra(POSITION_OF_CLICKED,0)
        val slug = intent.getStringExtra(WEBLINK_SLUG)
        var date = intent.getStringExtra(WEBLINK_DATE)
        var type = intent.getStringExtra(CONTENT_TYPE)

        Log.i("LOGGING", "This is the passed position $pos")
        Log.i("LOGGING", "This is the passed slug and date $slug     $date")

        var url ="https://ign.com/" // need a different link for videos
        url = url+ type +"s/" + date + "/" + slug
        Log.i("LOGGING", "This is the url $url")

        var myWebView : WebView? = null
        myWebView = webview
        myWebView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        myWebView.getSettings().setDomStorageEnabled(true);
        myWebView.getSettings().setJavaScriptEnabled(true)
        myWebView!!.loadUrl(url)

    }
}
