package com.forzz.android.vkinternshipgiphy.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.forzz.android.vkinternshipgiphy.domain.model.Gif
import com.forzz.android.vkinternshipgiphy.domain.use_cases.GetGifsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GifListScreenViewModel @Inject constructor(private val getGifsUseCase: GetGifsUseCase) :
    ViewModel() {

    private val _gifs = MutableLiveData<List<Gif>>()
    val gifs: LiveData<List<Gif>> = _gifs

    fun fetchGifs(
        apiKey: String,
        query: String,
        limit: Int,
        offset: Int,
        rating: String,
        lang: String
    ) {
        getGifsUseCase.setParams(apiKey, query, limit, offset, rating, lang)
        getGifsUseCase.execute(onSuccess = {
            _gifs.postValue(it)
            Log.d("GIF_URL", it[0].urlPreview)
        }, onError = {
            Log.d("EXECUTING_ERROR", it.localizedMessage)
        })
    }

    override fun onCleared() {
        super.onCleared()
        getGifsUseCase.dispose()
    }
}