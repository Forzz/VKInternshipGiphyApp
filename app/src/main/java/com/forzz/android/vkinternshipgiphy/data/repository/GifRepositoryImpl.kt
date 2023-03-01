package com.forzz.android.vkinternshipgiphy.data.repository

import com.forzz.android.vkinternshipgiphy.data.remote.GiphyApi
import com.forzz.android.vkinternshipgiphy.domain.model.Gif
import com.forzz.android.vkinternshipgiphy.domain.repository.GifRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class GifRepositoryImpl(private val api: GiphyApi): GifRepository {
    override fun getGifs(
        apiKey: String,
        query: String,
        limit: Int,
        offset: Int,
        rating: String,
        lang: String
    ): Single<List<Gif>> {
        return api.search(apiKey, query, limit, offset, rating, lang)
            .map { it.gifs }
    }
}