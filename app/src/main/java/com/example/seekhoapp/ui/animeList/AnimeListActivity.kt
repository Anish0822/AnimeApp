package com.example.seekhoapp.ui.animeList

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seekhoapp.R
import com.example.seekhoapp.constant.Constants
import com.example.seekhoapp.databinding.ActivityAnimeListBinding
import com.example.seekhoapp.repository.AnimeRepository
import com.example.seekhoapp.ui.adapter.AnimeAdapter
import com.example.seekhoapp.ui.animeDetail.AnimeDetailActivity

class AnimeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeListBinding
    private lateinit var viewModel: AnimeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = AnimeRepository()
        val viewModelFactory = AnimeListViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[AnimeListViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initView()
        observeResponse()
    }

    private fun initView() {
        viewModel.fetchAnimeList()
        binding.toolbar.tvTitle.text = getString(R.string.anime_ist)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun observeResponse() {
        viewModel.animeList.observe(this) { response ->
            binding.recyclerView.visibility = View.VISIBLE
            binding.recyclerView.adapter = AnimeAdapter(response.data) { animeId ->
                val intent = Intent(this, AnimeDetailActivity::class.java)
                intent.putExtra(Constants.ANIME_ID, animeId)
                startActivity(intent)
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

}