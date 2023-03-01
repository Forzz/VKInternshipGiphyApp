package com.forzz.android.vkinternshipgiphy.domain.repository

import com.forzz.android.vkinternshipgiphy.data.remote.dto.GifDTO
import com.forzz.android.vkinternshipgiphy.domain.model.Gif
import dagger.Provides
import io.reactivex.Single

interface GifRepository {

    fun getGifs(apiKey: String, query: String, limit: Int, offset: Int, rating: String, lang: String): Single<List<Gif>>
}