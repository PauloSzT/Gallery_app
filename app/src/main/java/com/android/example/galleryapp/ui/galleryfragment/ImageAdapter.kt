package com.android.example.galleryapp.ui.galleryfragment


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.example.gallery_app.databinding.ItemSmallImageBinding
import com.android.example.galleryapp.models.StorageImageCollection

class ImageAdapter(
    private val data: StorageImageCollection,
    private val context: Context,
    private var callBack: (Boolean) -> Unit
) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val smallImage = ItemSmallImageBinding.inflate(inflater, parent, false)
        return ViewHolder(smallImage)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return data.items.size
    }

    inner class ViewHolder(val binding: ItemSmallImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val image = binding.imageView1

        @SuppressLint("NotifyDataSetChanged")
        fun bind(position: Int) {
            val item = data.items[position]
            val bitmap = BitmapFactory.decodeFile(item.pathName)
            image.setImageBitmap(bitmap)
            binding.checkbox.isChecked = item.isSelected
            binding.checkbox.visibility =
                if (data.areItemsBeingSelected) View.VISIBLE else View.INVISIBLE
            image.setOnLongClickListener {
                if (!item.isSelected && !data.areItemsBeingSelected) {
                    item.isSelected = true
                    data.areItemsBeingSelected = true
                    notifyDataSetChanged()
                    callBack(true)
                }
                true
            }
            image.setOnClickListener {
                if (data.areItemsBeingSelected) {
                    if (item.isSelected) {
                        item.isSelected = false
                        if (data.items.none { it.isSelected }) {
                            data.areItemsBeingSelected = false
                            callBack(false)
                        }
                    } else {
                        item.isSelected = true
                    }
                    notifyDataSetChanged()
                }
            }

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
