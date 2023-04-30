package com.android.example.galleryapp.ui.carrouselfragment


import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.android.example.gallery_app.R
import com.android.example.gallery_app.databinding.FragmentCarrouselBinding
import com.android.example.galleryapp.models.StorageImage
import java.io.File


class CarrouselFragment : Fragment() {

    private lateinit var binding: FragmentCarrouselBinding
    val folder = Environment.getExternalStorageDirectory()
    private val imagesList = File(folder, "/Pictures/CameraX-Image")
        .listFiles()?.mapIndexed{ index, item ->
            StorageImage(pathName = item.path, id = index)
        }
    val selectedPhotos = arguments?.getIntegerArrayList("photo_List")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarrouselBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedPhotos?.forEach { Log.wtf("paulo", "${it}") }

        imagesList?.let{ list ->
            val selectedItemsList = list.filter { selectedPhotos?.contains(it.id) ?: false }
            binding.recyclerView.adapter = ImageSliderAdapter(selectedItemsList, requireContext())
        }

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.layoutManager = layoutManager

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerView)

    }
}
