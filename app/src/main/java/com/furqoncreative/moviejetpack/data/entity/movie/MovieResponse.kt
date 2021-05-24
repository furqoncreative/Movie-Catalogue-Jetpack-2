package com.furqoncreative.moviejetpack.data.entity.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	var results: List<MovieEntity>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)


