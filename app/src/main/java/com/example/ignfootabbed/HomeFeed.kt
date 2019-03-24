package com.example.ignfootabbed

/*****  Used to extract data from the API   *****/

class HomeFeed(val count : Int, val startIndex: Int, val data: List<content>){

}

class content(val contentId: String, val contentType: String, val thumbnails: List<Thumbnails>,
              val metadata: Metablata, val tags: Array<String>, val authors: Array<String>) {

}

class Thumbnails(val url: String, val size: String ,val width : Int, val height : Int){ //Model object. Used to get info from REST API

}

class Metablata(val title: String, val headline: String?, val description : String,
                val publishDate : String , val slug : String, val networks : Array<String>,
                val state : String, val duration: Int, val videoSeries: String) {

}

class ArticleClass(val data: List<content>){

}

class VideoClass(val data: content){

}
