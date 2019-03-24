package com.example.ignfootabbed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_rows.view.*


class MainAdapter(val contentData:MutableList<content>): RecyclerView.Adapter<CustomeViewHolder>(){

    override fun getItemCount(): Int {
        return contentData.count()
    }

    override fun onCreateViewHolder(p0: ViewGroup, viewType : Int): CustomeViewHolder {
        //Creating a view
        val layoutInflater = LayoutInflater.from(p0?.context)
        val cellForRow = layoutInflater.inflate(R.layout.recycler_rows, p0, false)

        return CustomeViewHolder(cellForRow)
    }

    override fun onBindViewHolder( holder: CustomeViewHolder, position: Int) {

        var publishDate = contentData[position].metadata.publishDate
            publishDate = publishDate.take(10).replace("-", "/") //Taking away time so that date is the only thing that is left

        if(contentData[position].metadata.headline != null) //Headline is for articles, title is for videos
            holder.itemView.textView_title.text = contentData[position].metadata.headline
        else
            holder.itemView.textView_title.text = contentData[position].metadata.title

        if(contentData[position].metadata.description != null)
            holder.itemView.textView_Description.text = contentData[position].metadata.description
        else
            holder.itemView.textView_Description.text = "No Description"
        holder.itemView.textView_date.text = publishDate


        if(contentData[position].contentType == "article") { //Bottom left of rv that help indicate if content it article or video
            holder.itemView.tv_typeOfContent.text = "Read"
            holder.itemView.play_button.visibility = View.GONE //Hides play button
        }
        else {
            holder.itemView.tv_typeOfContent.text = "Watch"
            holder.itemView.play_button.setBackgroundResource(R.drawable.play_button)
        }

        val thumbnailSize = 2 //Need to have function that will pick image size (compact, med, large)

        val thumbnailURL= contentData[position].thumbnails[thumbnailSize].url
        val thumbnailView = holder.itemView.imageView_thumb

        Picasso.get().load(thumbnailURL).into(thumbnailView)

        holder.setBorders()
        holder.webLinkSlug = contentData[position].metadata.slug
        holder.contentType = contentData[position].contentType
        holder.webLinkDate = publishDate
    }
}

