package com.android.example.galleryapp.ui.galleryfragment

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.android.example.gallery_app.databinding.FragmentGalleryBinding
import com.android.example.galleryapp.models.StorageImages
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

    val folder = Environment.getExternalStorageDirectory()
    private val imagesList = File(folder, "/Pictures/CameraX-Image").listFiles()?.map {
        StorageImages(it.path)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imagesList?.let { list ->
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
            binding.recycler.adapter = ImageAdapter(list, requireContext())
        }
    }
}
