package com.forzz.android.vkinternshipgiphy.data.mappers

import com.forzz.android.vkinternshipgiphy.data.remote.dto.GifDTO
import com.forzz.android.vkinternshipgiphy.domain.model.Gif

fun GifDTO.toGif(): Gif = Gif(
    id = id,
    user = username,
    title = title,
    importDatetime = import_datetime,
    frames = images.original.frames.toInt(),
    urlOriginal = images.original.url,
    urlPreview = images.preview_gif.url
)