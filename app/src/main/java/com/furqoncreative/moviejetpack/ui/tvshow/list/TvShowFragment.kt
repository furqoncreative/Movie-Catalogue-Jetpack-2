package com.furqoncreative.moviejetpack.ui.tvshow.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.furqoncreative.moviejetpack.databinding.TvShowFragmentBinding
import com.furqoncreative.moviejetpack.ui.tvshow.detail.TvShowDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment(), TvShowAdapter.TvShowItemListener {

    private val viewModel: TvShowViewModel by viewModels()
    private lateinit var binding: TvShowFragmentBinding
    private lateinit var adapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TvShowFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        getTvShows()
    }

    private fun setupRecyclerView() {
        adapter = TvShowAdapter(this)
        with(binding) {
            rvTvShow.layoutManager = LinearLayoutManager(requireContext())
            rvTvShow.adapter = adapter
        }
    }

    private fun getTvShows() {
        viewModel.getTvShows().observe(viewLifecycleOwner, {
            binding.progressBar.visibility = View.GONE
            adapter.setItems(ArrayList(it))
        })
    }

    override fun onClicked(tvShowId: Int?) {
        val intent = Intent(requireContext(), TvShowDetailActivity::class.java)
        intent.putExtra(TvShowDetailActivity.ID, tvShowId)
        startActivity(intent)
    }

}