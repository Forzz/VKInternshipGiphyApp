package com.forzz.android.vkinternshipgiphy.presentation

import android.R
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.target.Target
import com.forzz.android.vkinternshipgiphy.databinding.GifItemBinding
import com.forzz.android.vkinternshipgiphy.domain.model.Gif


internal class GifsAdapter(val gifListClickListener: GifListClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val gifs: MutableList<Gif> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GifItemBinding.inflate(inflater, parent, false)

        return GifsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GifsViewHolder).onBind(getItem(position))
    }

    override fun getItemCount(): Int = gifs.size

    private fun getItem(position: Int): Gif = gifs[position]

    inner class GifsViewHolder(
        private val binding: GifItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(gif: Gif) {
            Glide.with(binding.root.context)
                .asGif()
                .load(gif.urlPreview)
                .transform(FitCenter(), RoundedCorners(6))
                .into(binding.gifImage)

            binding.gifImage.setOnClickListener {
                gifListClickListener.onGifListItemClick(it, gif)
            }
        }
    }

    fun addData(list: List<Gif>) {
        this.gifs.clear()
        this.gifs.addAll(list)
        notifyDataSetChanged()
    }
}