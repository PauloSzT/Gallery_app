package com.android.example.galleryapp.models

data class StorageImageCollection(
    var areItemsBeingSelected: Boolean = false,
    val items: List<StorageImage>
)
