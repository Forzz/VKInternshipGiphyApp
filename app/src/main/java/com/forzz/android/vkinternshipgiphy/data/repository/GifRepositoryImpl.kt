package com.forzz.android.vkinternshipgiphy.data.repository

import com.forzz.android.vkinternshipgiphy.data.mappers.toGif
import com.forzz.android.vkinternshipgiphy.data.remote.GiphyApi
import com.forzz.android.vkinternshipgiphy.domain.model.Gif
import com.forzz.android.vkinternshipgiphy.domain.repository.GifRepository
import io.reactivex.Single


class GifRepositoryImpl(private val api: GiphyApi) : GifRepository {

    override fun getGifs(
        apiKey: String,
        query: String,
        limit: Int,
        offset: Int,
        rating: String,
        lang: String
    ): Single<List<Gif>> {
        return api.search(apiKey, query, limit, offset, rating, lang)
            .map { response ->
                response.gifs.map {
                    it.toGif()
                }
            }
    }
}