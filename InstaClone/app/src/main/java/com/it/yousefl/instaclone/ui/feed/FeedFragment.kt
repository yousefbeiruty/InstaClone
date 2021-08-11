package com.it.yousefl.instaclone.ui.feed

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.Coil
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.it.yousefl.instaclone.R
import com.it.yousefl.instaclone.databinding.FragmentFeedBinding

class FeedFragment : Fragment() {

    companion object {
        fun newInstance() = FeedFragment()
    }

    private val viewModel: FeedViewModel by viewModels()
    private val adapter = FeedRecyclerAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val feed = arguments?.getString("feed")//TODO:turn into enum
        feed?.let { viewModel.updateFeed(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentFeedBinding.inflate(inflater, container, false)
        binding.rcGalleryFeed.layoutManager = LinearLayoutManager(requireContext())
        binding.rcGalleryFeed.adapter = adapter
        viewModel.feed.observe(viewLifecycleOwner) {
            it.forEach {
                val request= ImageRequest.Builder(requireContext())
                    .data(it.link)
                    .size( binding.rcGalleryFeed.width)
                    .build()
                Coil.imageLoader(requireContext()).enqueue(request)
            }
           adapter.submitList(it)
        }


        return binding.root
    }


}