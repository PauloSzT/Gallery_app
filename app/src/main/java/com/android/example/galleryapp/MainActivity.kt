package com.android.example.galleryapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import com.android.example.gallery_app.R
import com.android.example.gallery_app.databinding.ActivityMainBinding
import com.android.example.galleryapp.ui.camerafragment.CameraFragment
import com.android.example.galleryapp.ui.carrouselfragment.CarrouselFragment
import com.android.example.galleryapp.ui.galleryfragment.GalleryFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        binding.switch1.setOnClickListener {
//            val sharedPreferences = getSharedPreferences("Mode", Context.MODE_PRIVATE)
//            val editor = sharedPreferences.edit()
//            val nightMode = sharedPreferences.getBoolean("night", false)
//            if (nightMode) {
//                binding.switch1.isChecked = true
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            }
//            binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
//                if (!isChecked) {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                    editor.putBoolean("night", false)
//                    editor.apply()
//                } else {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                    editor.putBoolean("night", true)
//                    editor.apply()
//                }
//            }
//        }
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.myNavHostFragment, GalleryFragment())
            .commit()

    }
}
