package com.forzz.android.vkinternshipgiphy.presentation

import android.os.Handler
import android.os.Looper
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
    var text = MutableLiveData<String>()
    var apiKey = MutableLiveData<String>()

    private val handler = Handler(Looper.getMainLooper())
    private var runnable: Runnable? = null

    fun search(query: String) {
        runnable?.let { handler.removeCallbacks(it) }
        runnable = Runnable {
            fetchGifs(apiKey.value!!, query)
        }
        handler.postDelayed(runnable!!, 1200)
    }

    private fun fetchGifs(
        apiKey: String, query: String
    ) {
        val limit: Int = 25
        val offset: Int = 0
        val rating: String = "g"
        val lang: String = "en"

        getGifsUseCase.setParams(apiKey, query, limit, offset, rating, lang)
        getGifsUseCase.execute(onSuccess = {
            _gifs.postValue(it)
        }, onError = {
            Log.e("EXECUTING_ERROR", it.localizedMessage)
        })
    }

    override fun onCleared() {
        super.onCleared()
        getGifsUseCase.dispose()
    }
}