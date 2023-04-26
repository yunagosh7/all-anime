package com.example.allanime.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allanime.R
import com.example.allanime.adapter.AnimeGenreAdapter
import com.example.allanime.databinding.ActivityAnimeDetailBinding
import com.example.allanime.datamodels.AnimeDetail
import com.example.allanime.datamodels.AnimeDetailData
import com.example.allanime.viewmodel.AnimeDetailViewModel
import com.example.allanime.viewmodel.AnimeDetailViewModelFactory
import com.example.allanime.viewmodel.HomeViewModel
import com.example.allanime.viewmodel.HomeViewModelFactory
import com.squareup.picasso.Picasso

class AnimeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeDetailBinding

    private val viewModel: AnimeDetailViewModel by lazy {
        val activity = requireNotNull(this) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, AnimeDetailViewModelFactory(activity.application)).get(
            AnimeDetailViewModel::class.java
        )
    }

    private lateinit var currentAnime: AnimeDetailData


    // Info recibida del intent
    private lateinit var id: String
    private lateinit var name: String
    private lateinit var image: String

    private lateinit var genreAdapter: AnimeGenreAdapter

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_IMAGE = "extra_image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getStringExtra(EXTRA_ID).orEmpty()
        name = intent.getStringExtra(EXTRA_NAME).orEmpty()
        image = intent.getStringExtra(EXTRA_IMAGE).orEmpty()


        viewModel.getAnimeById(id)

        initUI()

        binding.fabAddItem.setOnClickListener {
            viewModel.insertItemToDatabase(currentAnime.data)
        }


    }

    private fun initUI() {
        Picasso.get().load(image).into(binding.ivDetailImage)
        binding.tvDetailName.text = name

        viewModel.anime.observe(this) { anime ->
            val icon = if (anime.data.isSaved) {
                R.drawable.ic_add_favorite
            } else {
                R.drawable.ic_favorites
            }
            binding.fabAddItem.setImageResource(icon)



            with(anime.data) {
                binding.tvDetailEpisodes.text = episodes.toString()
                binding.tvDetailRate.text = score.toString()
                genreAdapter = AnimeGenreAdapter(genres)
                binding.rvAnimeGenres.layoutManager =
                    LinearLayoutManager(this@AnimeDetailActivity, LinearLayoutManager.HORIZONTAL, false)
                binding.rvAnimeGenres.adapter = genreAdapter
                binding.tvDetailDesc.text = synopsis
            }
            currentAnime = anime
        }


    }
}