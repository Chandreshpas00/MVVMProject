package com.example.mvvmproject.ui.theme

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import androidx.lifecycle.repeatOnLifecycle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mvvmproject.R

class PostsFragment : Fragment() {

    private val viewModel: PostsViewModel by viewModels()
    private lateinit var adapter: PostsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_posts, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_posts)
        val progress = view.findViewById<ProgressBar>(R.id.progress)
        val errorText = view.findViewById<TextView>(R.id.text_error)
        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe_container)

        adapter = PostsAdapter()
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        swipe.setOnRefreshListener { viewModel.fetchPosts() }

        // Collect StateFlow safely with repeatOnLifecycle
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is UiState.Loading -> {
                            progress.visibility = View.VISIBLE
                            recycler.visibility = View.INVISIBLE
                            errorText.visibility = View.GONE
                            swipe.isRefreshing = false
                        }
                        is UiState.Success -> {
                            progress.visibility = View.GONE
                            recycler.visibility = View.VISIBLE
                            errorText.visibility = View.GONE
                            adapter.submitList(state.data)
                            swipe.isRefreshing = false
                        }
                        is UiState.Error -> {
                            progress.visibility = View.GONE
                            recycler.visibility = View.INVISIBLE
                            errorText.visibility = View.VISIBLE
                            errorText.text = state.message
                            swipe.isRefreshing = false
                        }
                    }
                }
            }
        }
    }
}
