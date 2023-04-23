package com.example.allanime.datamodels

import com.google.gson.annotations.SerializedName

data class AnimeTopList(
    @SerializedName("data") val data: List<Anime>
)
