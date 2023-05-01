package com.android.example.galleryapp.ui.carrouselfragment

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.example.gallery_app.databinding.ItemSliderBinding
import com.android.example.galleryapp.models.StorageImage

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

    inner class ViewHolder(binding: ItemSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val image = binding.imageView

        fun bind(position: Int) {
            val item = data[position]
            val bitmap = BitmapFactory.decodeFile(item.pathName)
            image.setImageBitmap(bitmap)
        }
    }
}
