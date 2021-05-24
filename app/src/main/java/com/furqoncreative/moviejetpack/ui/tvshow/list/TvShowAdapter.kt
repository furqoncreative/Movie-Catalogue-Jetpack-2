package com.furqoncreative.moviejetpack.ui.tvshow.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.furqoncreative.moviejetpack.BuildConfig
import com.furqoncreative.moviejetpack.data.entity.tvshow.TvShowEntity
import com.furqoncreative.moviejetpack.databinding.ItemRowMovieTvBinding

class TvShowAdapter(private val listener: TvShowItemListener) :
    RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    interface TvShowItemListener {
        fun onClicked(tvShowId: Int?)
    }

    private val items = ArrayList<TvShowEntity>()

    fun setItems(items: ArrayList<TvShowEntity>) {
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
        private val listener: TvShowItemListener
    ) : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var tvShow: TvShowEntity

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: TvShowEntity) {
            this.tvShow = item
            itemBinding.itemTitle.text = item.name
            itemBinding.itemDate.text = item.firstAirDate
            itemBinding.itemMovieRating.text = item.voteAverage.toString()
            Glide.with(itemBinding.root)
                .load(BuildConfig.BASE_IMAGE_URL + item.posterPath)
                .into(itemBinding.itemPoster)
        }

        override fun onClick(v: View?) {
            listener.onClicked(tvShow.id)
        }
    }

}


