package com.example.allanime.datamodels

import com.google.gson.annotations.SerializedName

data class AnimeDetailData (
    @SerializedName("data") val data: AnimeDetail
        )

data class AnimeDetail(
    @SerializedName("title") val name: String,
    @SerializedName("images") val image: AnimeImage,
    @SerializedName("episodes") val episodes: Int,
    @SerializedName("synopsis") val synopsis: String,
    @SerializedName("score") val score: Float,
    @SerializedName("genres") val genres: List<AnimeGenre>
)

data class AnimeGenre(
    @SerializedName("name") val name: String
)