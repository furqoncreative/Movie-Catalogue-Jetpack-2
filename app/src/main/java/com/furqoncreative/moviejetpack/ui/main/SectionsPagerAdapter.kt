package com.furqoncreative.moviejetpack.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.furqoncreative.moviejetpack.ui.movie.list.MovieFragment
import com.furqoncreative.moviejetpack.ui.tvshow.list.TvShowFragment

class SectionsPagerAdapter(
    fa: FragmentActivity
) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieFragment()
            1 -> TvShowFragment()
            else -> MovieFragment()
        }
    }

}