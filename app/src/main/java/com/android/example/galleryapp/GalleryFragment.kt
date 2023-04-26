package com.android.example.galleryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.android.example.gallery_app.R
import com.android.example.gallery_app.databinding.FragmentGalleryBinding


class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cameraButton.setOnClickListener {
            val action = GalleryFragmentDirections.actionGalleryFragmentToCameraFragment()
           findNavController().navigate(action)
        }

//        binding.galleryButton.setOnClickListener {
//            // Request permission to access device gallery
//            // Launch Gallery Intent to select images
//            // Save selected images to external storage
//            // Navigate to Image List Screen Fragment
//        }
    }
}