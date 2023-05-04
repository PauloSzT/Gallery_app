package com.android.example.galleryapp.ui.galleryfragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.android.example.gallery_app.databinding.FragmentGalleryBinding
import com.android.example.galleryapp.models.StorageImage
import com.android.example.galleryapp.models.StorageImageCollection
import java.io.File

class GalleryFragment : Fragment() {
    private lateinit var binding: FragmentGalleryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        binding.cameraButton.setOnClickListener {
            val action = GalleryFragmentDirections.actionGalleryFragmentToCameraFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }

    private val folder = Environment.getExternalStorageDirectory()
    private val imagesList = File(folder, CHILD_ROUTE)
        .listFiles()?.mapIndexed { index, item ->
            StorageImage(pathName = item.path, id = index)
        }

    private var imagesCollection = StorageImageCollection(false, imagesList ?: emptyList())
    private lateinit var adapter: ImageAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ImageAdapter(imagesCollection, requireContext()) { value ->
            binding.selectButton.visibility = if (value) View.VISIBLE else View.INVISIBLE
        }
        binding.selectButton.setOnClickListener {
            val selectedItems =
                imagesCollection.items.filter { it.isSelected }.map { it.id }.toIntArray()
            val action =
                GalleryFragmentDirections.actionGalleryFragmentToCarrouselFragment(selectedItems)
            findNavController().navigate(action)
        }

        imagesList?.let {
            val gridLayout = GridLayoutManager(requireContext(), 2)
            gridLayout.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (position % 3) {
                        0, 1 -> 1
                        2 -> 2
                        else -> 2
                    }
                }
            }
            binding.recycler.layoutManager = gridLayout

            binding.recycler.adapter = adapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        val newImagesList = File(folder, CHILD_ROUTE)
            .listFiles()?.mapIndexed { index, item ->
                StorageImage(pathName = item.path, id = index)
            }
        if(newImagesList != imagesList){
            adapter.data = StorageImageCollection(false, newImagesList ?: emptyList())
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        private const val CHILD_ROUTE = "/Pictures/CameraX-Image"
    }
}
