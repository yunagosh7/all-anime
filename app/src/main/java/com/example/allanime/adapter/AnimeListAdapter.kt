package com.example.allanime.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allanime.R
import com.example.allanime.databinding.ItemAnimeBinding
import com.example.allanime.datamodels.Anime

class AnimeListAdapter(
    var animeList: List<Anime>,
    val onItemClick: (String, String, String) -> Unit
    ) : RecyclerView.Adapter<AnimeListViewHolder>() {

//    fun changeList(list: List<Anime>) {
//        animeList = list
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_anime, parent, false)
        return AnimeListViewHolder(view)

    }

    override fun onBindViewHolder(holder: AnimeListViewHolder, position: Int) {
        holder.bind(animeList[position], onItemClick)
    }

    override fun getItemCount(): Int = animeList.size

}