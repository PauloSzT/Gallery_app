package com.android.example.galleryapp.ui.galleryfragment

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.example.gallery_app.databinding.FragmentGalleryBinding
import java.io.File

class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }
    val folder = Environment.getExternalStorageDirectory()
    private val imagesList = File(folder,"/Pictures/CameraX-Image").listFiles()?.map{
        StorageImages(it.path)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cameraButton.setOnClickListener {
            val action = GalleryFragmentDirections.actionGalleryFragmentToCameraFragment()
            findNavController().navigate(action)
        }
        val grid = binding.gridView
        imagesList?.let {
            grid.adapter = ImageAdapter(it)
        }
    }
}
