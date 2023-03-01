package com.forzz.android.vkinternshipgiphy.data.remote

import com.forzz.android.vkinternshipgiphy.data.remote.dto.GifDTO
import com.google.gson.annotations.SerializedName

data class GiphyResponse(
    @SerializedName("data")
    val gifs: List<GifDTO>
)
