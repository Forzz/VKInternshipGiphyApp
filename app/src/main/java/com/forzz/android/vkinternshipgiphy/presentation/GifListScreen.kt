package com.forzz.android.vkinternshipgiphy.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.forzz.android.vkinternshipgiphy.R
import com.forzz.android.vkinternshipgiphy.databinding.GifListScreenFragmentBinding
import com.forzz.android.vkinternshipgiphy.domain.model.Gif
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GifListScreen : Fragment(), GifListClickListener {

    private lateinit var binding: GifListScreenFragmentBinding
    private lateinit var adapter: GifsAdapter
    private lateinit var apiKey: String
    private lateinit var listener: GifListClickListener
    private val viewModel: GifListScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        apiKey = resources.getString(R.string.giphy_api_key)
        viewModel.apiKey.value = apiKey
        listener = this
        adapter = GifsAdapter(listener)
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

        viewModel.gifs.observe(viewLifecycleOwner) {
            it?.let {
                initRecyclerView(it)
            }
        }

        binding.editTextSearchQuery.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.search(s.toString())
            }
        })

        return binding.root
    }

    private fun initRecyclerView(gifs: List<Gif>) {
        adapter.addData(gifs)
    }

    override fun onGifListItemClick(view: View, gif: Gif) {
        val action = GifListScreenDirections.actionGifListScreenToGifDeatils(gif)
        view.findNavController().navigate(action)
    }
}

