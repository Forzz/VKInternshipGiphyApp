package com.forzz.android.vkinternshipgiphy.domain.model

import java.io.Serializable

data class Gif(
    val id: String,
    val user: String,
    val title: String,
    val importDatetime: String,
    val frames: Int,
    val urlOriginal: String,
    val urlPreview: String
) : Serializable
