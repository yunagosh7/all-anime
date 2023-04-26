package com.example.allanime.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.allanime.databinding.ItemGenreBinding
import com.example.allanime.datamodels.AnimeGenre

class AnimeGenreViewHolder(val binding: ItemGenreBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(genre: AnimeGenre) {
        binding.tvAnimeGenre.text = genre.name
    }
}