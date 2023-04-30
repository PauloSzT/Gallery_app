package com.android.example.galleryapp.ui.galleryfragment


import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.example.gallery_app.R
import com.android.example.galleryapp.models.StorageImages

class ImageAdapter(
    private val data: List<StorageImages>,
    private val context: Context
) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val smallImage =
                LayoutInflater.from(context).inflate(R.layout.item_small_image, parent, false)
            return ViewHolder(smallImage)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(binding: View) : RecyclerView.ViewHolder(binding) {

        val image = binding.findViewById<ImageView>(R.id.imageView1)
        fun bind(position: Int) {
            val bitmap = BitmapFactory.decodeFile(data[position].pathName)
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
