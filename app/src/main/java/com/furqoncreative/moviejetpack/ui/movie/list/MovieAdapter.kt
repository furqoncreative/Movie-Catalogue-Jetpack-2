package com.furqoncreative.moviejetpack.ui.movie.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.furqoncreative.moviejetpack.BuildConfig.BASE_IMAGE_URL
import com.furqoncreative.moviejetpack.data.entity.movie.MovieEntity
import com.furqoncreative.moviejetpack.databinding.ItemRowMovieTvBinding

class MovieAdapter(private val listener: MovieItemListener) :
    RecyclerView.Adapter<MovieAdapter.TvShowViewHolder>() {

    interface MovieItemListener {
        fun onClicked(movieId: Int?)
    }

    private val items = ArrayList<MovieEntity>()

    fun setItems(items: ArrayList<MovieEntity>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding: ItemRowMovieTvBinding =
            ItemRowMovieTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) =
        holder.bind(items[position])

    inner class TvShowViewHolder(
        private val itemBinding: ItemRowMovieTvBinding,
        private val listener: MovieItemListener
    ) : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var movie: MovieEntity

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: MovieEntity) {
            this.movie = item
            itemBinding.itemTitle.text = item.title
            itemBinding.itemDate.text = item.releaseDate
            itemBinding.itemMovieRating.text = item.voteAverage.toString()
            Glide.with(itemBinding.root)
                .load(BASE_IMAGE_URL + item.posterPath)
                .into(itemBinding.itemPoster)
        }

        override fun onClick(v: View?) {
            listener.onClicked(movie.id)
        }
    }

}


