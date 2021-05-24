Skenario Unit Test :

1. MovieViewModelTest

- menguji fungsi getMovies, yaitu dengan mencocokan jumlah item yang diambil melalui ViewModel dengan jumlah data dummy movie sebenarnya (10).

2. MovieDetailViewModelTest

- menguji fungsi getMovie, yaitu dengan mencocokan data yang diambil melalui ViewModel dengan data dummy movie sebenarnya (data pada index 0).

3. TvShowViewModelTest :

- menguji fungsi getTvShows, yaitu dengan mencocokan jumlah item yang diambil melalui ViewModel dengan jumlah data dummy tv show sebenarnya (10).

4. TvShowDetailViewModelTest

- menguji fungsi getTvShow, yaitu dengan mencocokan data yang diambil melalui ViewModel dengan data dummy tv show sebenarnya (data pada index 0).

Skenario Instrumentation Test :

1. loadMovies()

- Menguji apakah RecyclerView dengan id rv_movie tampil atau tidak

- Menjalankan scrolling pada RecyclerView dengan id rv_movie sampai pada posisi paling akhir dari list.

2. loadDetailMovie()

- Melakukan klik pada RecyclerView dengan id rv_movie di posisisi 0 (id movie 1)

- Menguji apakah TextView dengan id txtTitle tampil atau tidak.

- Menguji apakah text pada TextView dengan id txtTitle sama dengan title dalam data dummy movie (id movie 1)

- Menguji apakah TextView dengan id txtGenre tampil atau tidak

- Menguji apakah text TextView dengan id txtGenre sama dengan genre dalam data dummy movie (id movie 1)

- Menguji apakah TextView dengan id txtRating tampil atau tidak

- Menguji apakah text TextView dengan id txtRating sama dengan rating dalam data dummy movie (id movie 1)

- Menguji apakah TextView dengan id txtOverview tampil atau tidak

- Menguji apakah text TextView dengan id txtOverview sama dengan overview dalam data dummy  movie (id  movie 1)

- Menguji apakah ImageView dengan id imgBackdrop tampil atau tidak.

- Menguji apakah ImageView dengan id imgPoster tampil atau tidak.

3. loadTvShows()

- Melakukan klik pada teks "Tv Show", untuk menuju tab layout tv show.

- Menguji apakah RecyclerView dengan id rv_tv_show tampil atau tidak

- Menjalankan scrolling pada RecyclerView dengan id rv_tv_show sampai pada posisi paling akhir dari list.

4. loadDetailTvShow()

- Melakukan klik pada teks "Tv Show", untuk menuju tab layout tv show.

- Melakukan klik pada RecyclerView dengan id rv_tv_show di posisisi 0 (id movie 1)

- Menguji apakah TextView dengan id txtTitle tampil atau tidak.

- Menguji apakah text pada TextView dengan id txtTitle sama dengan title dalam data dummy tv show (id  tv show 1)

- Menguji apakah TextView dengan id txtGenre tampil atau tidak

- Menguji apakah text TextView dengan id txtGenre sama dengan genre dalam data dummy  tv show (id  tv show 1)

- Menguji apakah TextView dengan id txtRating tampil atau tidak

- Menguji apakah text TextView dengan id txtRating sama dengan rating dalam data dummy  tv show (id  tv show 1)

- Menguji apakah TextView dengan id txtOverview tampil atau tidak

- Menguji apakah text TextView dengan id txtOverview sama dengan overview dalam data dummy  tv show (id  tv show 1)

- Menguji apakah ImageView dengan id imgBackdrop tampil atau tidak.

- Menguji apakah ImageView dengan id imgPoster tampil atau tidak.