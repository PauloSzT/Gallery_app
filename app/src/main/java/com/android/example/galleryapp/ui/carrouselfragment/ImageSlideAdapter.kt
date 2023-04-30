package com.android.example.galleryapp.ui.carrouselfragment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.example.gallery_app.R
import com.android.example.gallery_app.databinding.ItemSliderBinding
import com.android.example.gallery_app.databinding.ItemSmallImageBinding
import com.android.example.galleryapp.models.StorageImage
import com.android.example.galleryapp.models.StorageImageCollection
import com.android.example.galleryapp.ui.galleryfragment.ImageAdapter
import com.squareup.picasso.Picasso

class ImageSliderAdapter(
    private val data: List<StorageImage>,
    private val context: Context
) : RecyclerView.Adapter<ImageSliderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val image = ItemSliderBinding.inflate(inflater, parent, false)
        return ViewHolder(image)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(val binding: ItemSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val image = binding.imageView

        fun bind(position: Int) {
            val item = data[position]
            val bitmap = BitmapFactory.decodeFile(item.pathName)
            image.setImageBitmap(bitmap)
        }
    }

    companion object {
        private const val ZERO = 0
        private const val UNIT = 1
        private const val DOUBLE = 2
        private const val INDEX_ZERO = 0
        private const val INDEX_ONE = 1
        private const val INDEX_TWO = 2
        private const val DIV_FACTOR = 3
        private const val TIMES_FACTOR = 2
        private const val VIEW_TYPES = 2
    }
}