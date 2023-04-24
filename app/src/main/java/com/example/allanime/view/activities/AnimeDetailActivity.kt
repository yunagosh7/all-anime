package com.example.allanime.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.allanime.R
import com.example.allanime.databinding.ActivityAnimeDetailBinding
import com.example.allanime.viewmodel.AnimeDetailViewModel
import com.squareup.picasso.Picasso

class AnimeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeDetailBinding

    private lateinit var viewModel: AnimeDetailViewModel

    // Info recibida del intent
    private lateinit var id: String
    private lateinit var name: String
    private lateinit var image: String

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

        viewModel = ViewModelProvider(this).get(AnimeDetailViewModel::class.java)

        viewModel.getAnimeById(id)

        initUI()


    }

    private fun initUI() {
        Picasso.get().load(image).into(binding.ivDetailImage)
        binding.tvDetailName.text = name

        viewModel.anime.observe(this) { anime ->
            binding.tvDetailEpisodes.text = anime.episodes.toString()
            binding.tvDetailRate.text = anime.score.toString()
        }

    }
}