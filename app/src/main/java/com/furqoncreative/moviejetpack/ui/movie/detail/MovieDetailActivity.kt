package com.furqoncreative.moviejetpack.ui.movie.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.furqoncreative.moviejetpack.BuildConfig.BASE_IMAGE_URL
import com.furqoncreative.moviejetpack.databinding.AcitivityMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
    companion object{
        const val ID = "ID"
    }

    private val viewModel: MovieDetailViewModel by viewModels()
    private lateinit var binding: AcitivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcitivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent?.getIntExtra(ID, 0)?.let {
            getMovie(it)
        }

        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getMovie(id: Int) {
        viewModel.getMovie(id).observe(this, { item ->
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

                binding.txtTitle.text = item.title
                binding.txtOverview.text = item.overview
                binding.txtRating.text = item.voteAverage.toString()
                binding.txtDate.text = item.releaseDate

            }
        })
    }

}