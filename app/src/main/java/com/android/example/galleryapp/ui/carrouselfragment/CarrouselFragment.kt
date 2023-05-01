package com.android.example.galleryapp.ui.carrouselfragment


import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.android.example.gallery_app.databinding.FragmentCarrouselBinding
import com.android.example.galleryapp.models.StorageImage
import java.io.File

class CarrouselFragment : Fragment() {

    private lateinit var binding: FragmentCarrouselBinding
    private val folder = Environment.getExternalStorageDirectory()
    private val imagesList = File(folder, CHILD_ROUTE)
        .listFiles()?.mapIndexed { index, item ->
            StorageImage(pathName = item.path, id = index)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarrouselBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val args = bundle?.let { CarrouselFragmentArgs.fromBundle(it) }
        val selectedPhotos = args?.photoList

        imagesList?.let { list ->
            val selectedItemsList = list.filter { selectedPhotos?.contains(it.id) ?: false }
            binding.recyclerView.adapter = ImageSliderAdapter(selectedItemsList, requireContext())
        }

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.layoutManager = layoutManager

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerView)
    }

    companion object {
        private const val CHILD_ROUTE = "/Pictures/CameraX-Image"
    }
}
