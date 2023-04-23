package com.example.allanime.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.allanime.R
import com.example.allanime.databinding.ItemAnimeBinding
import com.example.allanime.datamodels.Anime
import com.squareup.picasso.Picasso

class AnimeListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val ivImage = view.findViewById<ImageView>(R.id.iv_anime_image)
    val tvName = view.findViewById<TextView>(R.id.tv_anime_name)

    fun bind(anime: Anime) {
        Picasso.get().load(anime.images.jpg.imageUrl).into(ivImage)
        tvName.text = anime.name
    }
}