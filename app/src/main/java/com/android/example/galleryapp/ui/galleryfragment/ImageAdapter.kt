package com.android.example.galleryapp.ui.galleryfragment


import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.android.example.gallery_app.R


class ImageAdapter(private val images: List<StorageImages>) : BaseAdapter() {

    private val VIEW_TYPE_BIG_IMAGE = 0
    private val VIEW_TYPE_SMALL_IMAGE = 1
    private var currentItem = 0
    var tryCounts = 2

    override fun getViewTypeCount(): Int {
        return 2
    }

    override fun getItemViewType(position: Int): Int {
        val image = images[position]
        return if ((position % 2) == 0) {
            VIEW_TYPE_SMALL_IMAGE
        } else {
            VIEW_TYPE_BIG_IMAGE
        }
    }

    override fun getCount(): Int {
        if (images.size % 3 > 0) return images.size.div(3).times(2) + 1
        return images.size.div(3).times(2)
    }

    override fun getItem(position: Int): List<StorageImages> {
        if (tryCounts > 0) {
            tryCounts--
            return emptyList()
        }
        if ((position % 2) == 0) {
            if (currentItem == images.size - 1) {
                return listOf(images[currentItem])
            } else {
                val itemsList = listOf(images[currentItem], images[currentItem + 1])
                currentItem += 2
                return itemsList
            }
        } else {
            val itemsList2 = listOf(images[currentItem])
            currentItem++
            return itemsList2
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view = convertView
        val context = parent?.context

        val image = getItem(position)
        val viewType = getItemViewType(position)
        if (image.isEmpty()) return LayoutInflater.from(context)
            .inflate(R.layout.item_big_image, parent, false)

        if (view == null || view.tag != viewType) {
            view = when (viewType) {
                VIEW_TYPE_BIG_IMAGE -> {
                    LayoutInflater.from(context).inflate(R.layout.item_big_image, parent, false)
                }

                else -> {
                    LayoutInflater.from(context).inflate(R.layout.item_small_image, parent, false)
                }
            }
            view?.tag = viewType
            if (view?.tag == VIEW_TYPE_BIG_IMAGE) {
                val bitmap = BitmapFactory.decodeFile(image[0].pathName)
                view.findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmap)
            } else {
                val bitmap1 = BitmapFactory.decodeFile(image[0].pathName)
                view?.findViewById<ImageView>(R.id.imageView1)?.setImageBitmap(bitmap1)
                if (image.size > 1) {
                    val bitmap2 = BitmapFactory.decodeFile(image[1].pathName)
                    view?.findViewById<ImageView>(R.id.imageView2)?.setImageBitmap(bitmap2)
                }
            }
        }
        return view
    }
}

data class StorageImages(
    val pathName: String
)
