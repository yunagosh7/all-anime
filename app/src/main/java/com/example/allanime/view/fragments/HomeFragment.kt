package com.example.allanime.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.allanime.R
import com.example.allanime.adapter.AnimeListAdapter
import com.example.allanime.databinding.FragmentHomeBinding
import com.example.allanime.network.AnimeApi
import com.example.allanime.view.activities.AnimeDetailActivity
import com.example.allanime.viewmodel.AnimeDetailViewModel
import com.example.allanime.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var animesAdapter: AnimeListAdapter
    private lateinit var binding: FragmentHomeBinding

    private var categorySelected = "anime"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initUI()
        initListeners()


    }

    private fun initListeners() {
        binding.tvManga.setOnClickListener {
            if (categorySelected == "anime") {
                binding.tvManga.setBackgroundResource(R.drawable.rounded_corner_item_selected)
                binding.tvAnime.setBackgroundResource(R.drawable.rounded_corner_item_unselected)
                changeToManga()
                categorySelected = "manga"
            }
        }

        binding.tvAnime.setOnClickListener {
            binding.tvAnime.setBackgroundResource(R.drawable.rounded_corner_item_selected)
            binding.tvManga.setBackgroundResource(R.drawable.rounded_corner_item_unselected)
            changeToAnime()
            categorySelected = "anime"
        }
    }

    private fun initUI() {
        viewModel.getAnimeTop()
        viewModel.animeList.observe(viewLifecycleOwner) {
            animesAdapter = AnimeListAdapter(it) {id, name, image ->
                onItemClick(id, name, image)
            }
            binding.rvAnimeList.layoutManager =
                GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            binding.rvAnimeList.adapter = animesAdapter
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun changeToManga() {
        viewModel.getMangaTop()
        viewModel.animeList.observe(viewLifecycleOwner) {
            animesAdapter = AnimeListAdapter(it) {id, name, image ->
                onItemClick(id, name, image)
            }
            binding.rvAnimeList.adapter = animesAdapter
            binding.rvAnimeList.visibility = View.VISIBLE
        }
    }

    private fun changeToAnime() {
        viewModel.getAnimeTop()
        viewModel.animeList.observe(viewLifecycleOwner) {
            animesAdapter = AnimeListAdapter(it) {id, name, image ->
                onItemClick(id, name, image)
            }
            binding.rvAnimeList.swapAdapter(animesAdapter, true)
            binding.rvAnimeList.visibility = View.VISIBLE
        }

    }

    private fun onItemClick(id: String, name: String, image: String) {
        Log.d("HomeFragment", "Id: $id")
        val intent = Intent(context, AnimeDetailActivity::class.java)
        intent.putExtra(AnimeDetailActivity.EXTRA_ID, id)
        intent.putExtra(AnimeDetailActivity.EXTRA_NAME, name)
        intent.putExtra(AnimeDetailActivity.EXTRA_IMAGE, image)
        startActivity(intent)
    }

}