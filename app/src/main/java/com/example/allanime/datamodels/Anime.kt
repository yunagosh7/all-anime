package com.example.allanime.datamodels

import com.google.gson.annotations.SerializedName

data class Anime(
    @SerializedName("title") val name: String,
    @SerializedName("images") val images: AnimeImage
)


data class AnimeImage (
    @SerializedName("jpg") val jpg: AnimeImageJpg
        )

data class AnimeImageJpg (
    @SerializedName("image_url") val imageUrl: String
        )