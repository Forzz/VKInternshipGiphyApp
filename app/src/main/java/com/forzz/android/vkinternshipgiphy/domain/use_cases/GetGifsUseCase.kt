package com.forzz.android.vkinternshipgiphy.domain.use_cases

import com.forzz.android.vkinternshipgiphy.domain.model.Gif
import com.forzz.android.vkinternshipgiphy.domain.repository.GifRepository
import com.forzz.android.vkinternshipgiphy.domain.use_cases.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetGifsUseCase @Inject constructor(private val repository: GifRepository) : SingleUseCase<List<Gif>>() {

    private var apiKey = ""
    private var query = ""
    private var limit = 0
    private var offset = 0
    private var rating = ""
    private var lang = ""

    fun setParams(apiKey: String, query: String, limit: Int, offset: Int, rating: String, lang: String) {
        this.apiKey = apiKey
        this.query = query
        this.limit = limit
        this.offset = offset
        this.rating = rating
        this.lang = lang
    }

    override fun buildUseCaseSingle(): Single<List<Gif>> {
        return repository.getGifs(apiKey, query, limit, offset, rating, lang)
    }

}