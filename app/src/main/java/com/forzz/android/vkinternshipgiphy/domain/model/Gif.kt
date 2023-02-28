package com.forzz.android.vkinternshipgiphy.domain.model

data class Gif(
    val id: String,
    val user: String,
    val title: String,
    val importDatetime: String,
    val frames: Int,
    val urlOriginal: String,
    val urlPreview: String
)
