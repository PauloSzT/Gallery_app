package com.android.example.galleryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.example.gallery_app.R
import com.android.example.gallery_app.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Agregar el Fragment al contenedor principal
        supportFragmentManager.beginTransaction()
            .replace(R.id.myNavHostFragment, GalleryFragment())
            .commit()
    }
}
