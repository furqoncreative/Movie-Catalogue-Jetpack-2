package com.furqoncreative.moviejetpack.ui.tvshow.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.furqoncreative.moviejetpack.BuildConfig.BASE_IMAGE_URL
import com.furqoncreative.moviejetpack.databinding.ActivityTvShowDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowDetailActivity : AppCompatActivity() {
    companion object{
        const val ID = "ID"
    }

    private val viewModel: TvShowDetailViewModel by viewModels()
    private lateinit var binding: ActivityTvShowDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent?.getIntExtra("ID", 0)?.let {
            getTvShow(it)
        }
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }


    private fun getTvShow(id: Int) {
        viewModel.getTvShow(id).observe(this, { item ->
            if (item != null) {
                binding.progressBar.visibility = View.GONE
                binding.labelOverview.visibility = View.VISIBLE
                binding.txtRating.visibility = View.VISIBLE

                Glide.with(binding.root)
                    .load(BASE_IMAGE_URL + item.posterPath)
                    .into(binding.imgPoster)

                Glide.with(binding.root)
                    .load(BASE_IMAGE_URL + item.backdropPath)
                    .into(binding.imgBackdrop)

                binding.txtTitle.text = item.name
                binding.txtOverview.text = item.overview
                binding.txtRating.text = item.voteAverage.toString()
                binding.txtDate.text = item.firstAirDate

            }
        })
    }
}