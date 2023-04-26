package com.example.allanime.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allanime.databinding.ItemGenreBinding
import com.example.allanime.datamodels.AnimeGenre

class AnimeGenreAdapter(val genreList: List<AnimeGenre>) : RecyclerView.Adapter<AnimeGenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeGenreViewHolder {

        val binding = ItemGenreBinding.inflate(LayoutInflater.from(parent.context))
        return AnimeGenreViewHolder(binding)

    }

    override fun onBindViewHolder(holder: AnimeGenreViewHolder, position: Int) {
        holder.bind(genreList[position])
    }

    override fun getItemCount(): Int = genreList.size
}