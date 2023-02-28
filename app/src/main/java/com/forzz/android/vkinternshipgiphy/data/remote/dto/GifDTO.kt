package com.forzz.android.vkinternshipgiphy.data.remote.dto

data class GifDTO(
    val type: String,
    val id: String,
    val url: String,
    val username: String,
    val source: String,
    val title: String,
    val rating: String,
    val content_url: String,
    val import_datetime: String,
    val images: Images
)