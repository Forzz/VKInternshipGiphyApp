package com.forzz.android.vkinternshipgiphy.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.forzz.android.vkinternshipgiphy.R
import com.forzz.android.vkinternshipgiphy.databinding.GifListScreenFragmentBinding
import com.forzz.android.vkinternshipgiphy.domain.model.Gif
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GifListScreen : Fragment() {

    private lateinit var binding: GifListScreenFragmentBinding
    private lateinit var adapter: GifsAdapter
    private lateinit var apiKey: String
    private val viewModel: GifListScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        adapter = GifsAdapter()
        apiKey = resources.getString(R.string.giphy_api_key)
        viewModel.fetchGifs(apiKey, "cat", 25, 0, "g", "en")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.gif_list_screen_fragment, container, false)
        binding.gifsListViewModel = viewModel
        binding.gifsRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.gifsRecyclerView.adapter = adapter

        binding.button.setOnClickListener {
            viewModel.fetchGifs(apiKey, "cat", 25, 0, "g", "en")
        }

        viewModel.gifs.observe(viewLifecycleOwner, Observer {
            it?.let {
                initRecyclerView(it)
            }
        })


        return binding.root
    }

    private fun initRecyclerView(gifs: List<Gif>) {
        adapter.addData(gifs)
    }
}

