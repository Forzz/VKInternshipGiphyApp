package com.forzz.android.vkinternshipgiphy.presentation

import androidx.lifecycle.MutableLiveData
import com.forzz.android.vkinternshipgiphy.domain.model.Gif

class GifViewModel(val gif: Gif) {

    val gifData = MutableLiveData<Gif>()

    init {
        gifData.value = gif
    }
}