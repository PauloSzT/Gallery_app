package com.android.example.galleryapp.ui.carrouselfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.example.gallery_app.R
import com.squareup.picasso.Picasso

//class ImageSliderAdapter(private val imageUrls: List<String>) :
//    RecyclerView.Adapter<ImageSliderAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.image_slider_layout, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val imageUrl = imageUrls[position]
//        Picasso.get().load(imageUrl).into(holder.imageView)
//    }
//
//    override fun getItemCount(): Int = imageUrls.size
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imageView: ImageView = itemView.findViewById(R.id.image_view)
//    }
//}