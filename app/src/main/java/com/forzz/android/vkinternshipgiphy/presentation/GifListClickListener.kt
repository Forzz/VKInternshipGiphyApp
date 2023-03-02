package com.forzz.android.vkinternshipgiphy.presentation

import android.view.View
import com.forzz.android.vkinternshipgiphy.domain.model.Gif

interface GifListClickListener {
    fun onGifListItemClick(view: View, gif: Gif)
}