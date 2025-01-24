package com.example.seekhoapp.ui.animeDetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.seekhoapp.R
import com.example.seekhoapp.constant.Constants
import com.example.seekhoapp.databinding.ActivityAnimeDetailBinding
import com.example.seekhoapp.repository.AnimeRepository

class AnimeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeDetailBinding
    private lateinit var viewModel: AnimeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = AnimeRepository()
        val viewModelFactory = AnimeDetailViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[AnimeDetailViewModel::class.java]

        initView()
    }

    private fun initView() {
        binding.toolbar.imgBackArrow.visibility = View.VISIBLE
        binding.toolbar.imgBackArrow.setOnClickListener { onBackPressed() }
        binding.toolbar.tvTitle.text = getString(R.string.anime_detail)

        val animeId = intent.getIntExtra(Constants.ANIME_ID, -1)
        if (animeId != -1) {
            viewModel.fetchAnimeDetails(animeId)
            observeResponse()
        } else {
            Toast.makeText(this, getString(R.string.msg_invalid_id), Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun observeResponse() {
        viewModel.animeDetails.observe(this) { response ->
            response?.let { anime ->
                binding.srlMain.visibility = View.VISIBLE
                binding.tvTitle.text = anime.data.title
                binding.tvSynopsis.text = anime.data.synopsis
                binding.tvGenres.text = anime.data.genres.joinToString { it.name }
                binding.tvEpisodes.text = getString(R.string.episodes, anime.data.episodes.toString())
                binding.tvRating.text = anime.data.score.toString()

                if (anime.data.trailer?.embed_url != null) {
                    setupWebView(anime.data.trailer.embed_url)
                } else {
                    binding.webViewTrailer.visibility = View.GONE
                    binding.ivPoster.visibility = View.VISIBLE
                    Glide.with(this)
                        .load(anime.data.images.jpg.large_image_url)
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(binding.ivPoster)
                }
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun setupWebView(videoUrl: String) {
        binding.webViewTrailer.apply {
            visibility = View.VISIBLE
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            loadUrl(videoUrl)
        }
        binding.ivPoster.visibility = View.GONE
    }
}
