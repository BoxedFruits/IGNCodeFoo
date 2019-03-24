package com.example.ignfootabbed

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recycler_rows.view.*

class CustomeViewHolder (v: View, var webLinkSlug: String? = null, var webLinkDate: String? = null, var contentType : String? = null): RecyclerView.ViewHolder(v){
    companion object {
        val POSITION_OF_CLICKED = "passedPosition"
        val WEBLINK_SLUG = "webLinkInfoSlug"
        val WEBLINK_DATE = "webLinkInfoDate"
        val CONTENT_TYPE = "ContentType"
    }

    init{
        v.setOnClickListener {
            val intent = Intent(v.context, webViewActivity::class.java)
            Log.i("LOGGING", adapterPosition.toString())
            Log.i("LOGGING", "This is the slug  + $webLinkSlug")
            intent.putExtra(POSITION_OF_CLICKED,adapterPosition)
            intent.putExtra(WEBLINK_SLUG,webLinkSlug)
            intent.putExtra(WEBLINK_DATE,webLinkDate)
            intent.putExtra(CONTENT_TYPE,contentType)
            v.context.startActivity(intent)
        }
    }

    fun setBorders(){ // Rounding corners
        val thumbnailSizeX = itemView.imageView_thumb.width
        val thumbnailSizeY = itemView.imageView_thumb.height

        var params : ViewGroup.LayoutParams = itemView.imgCorners.layoutParams
        params.width = thumbnailSizeX
        params.height = thumbnailSizeY
        params = itemView.imgCorners2.layoutParams
        params.width = thumbnailSizeX
        params.height = thumbnailSizeY
    }
}