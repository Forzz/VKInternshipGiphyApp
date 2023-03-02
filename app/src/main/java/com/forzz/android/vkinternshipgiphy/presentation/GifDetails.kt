package com.forzz.android.vkinternshipgiphy.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.forzz.android.vkinternshipgiphy.R
import com.forzz.android.vkinternshipgiphy.databinding.GifDetailsFragmentBinding
import com.forzz.android.vkinternshipgiphy.domain.model.Gif

class GifDetails : Fragment() {

    private lateinit var binding: GifDetailsFragmentBinding
    private lateinit var gif: Gif

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.gif_details_fragment, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments.let {
            gif = GifDetailsArgs.fromBundle(it!!).gif
        }

        Glide.with(requireContext())
            .asGif()
            .load(gif.urlOriginal)
            .transform(FitCenter(), RoundedCorners(6))
            .into(binding.gifImageView)

        binding.titleTextView.text = if (gif.title.isNotEmpty()) gif.title else "GIF"
        binding.userTextView.text =
            if (gif.user.isNotEmpty()) "Username: ${gif.user}" else "Username: Unknown"
        binding.importDateTextView.text =
            if (gif.importDatetime.isNotEmpty()) "Upload date: ${gif.importDatetime}" else "Upload date: unknown"
        binding.framesTextView.text = "Frames: ${gif.frames}"
    }

}