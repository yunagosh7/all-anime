package com.example.allanime.datamodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class AnimeDetailData(
    @SerializedName("data") val data: AnimeDetail,
)

data class AnimeDetail(
    @SerializedName("title") val name: String,
    @SerializedName("episodes") val episodes: Int,
    @SerializedName("synopsis") val synopsis: String,
    @SerializedName("images") val image: AnimeImage,
    @SerializedName("score") val score: Float,
    @SerializedName("genres") val genres: List<AnimeGenre>,
    var isSaved: Boolean = false
)

data class AnimeGenre(
    @SerializedName("name") val name: String
)