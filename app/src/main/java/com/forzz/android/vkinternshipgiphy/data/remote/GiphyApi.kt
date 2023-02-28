package com.forzz.android.vkinternshipgiphy.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {
    @GET("gifs/search")
    fun search(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
        @Query("lang") language: String
    ): Observable<GiphyResponse>
}