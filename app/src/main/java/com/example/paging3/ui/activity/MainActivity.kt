package com.example.paging3.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.paging3.databinding.ActivityMainBinding
import com.example.paging3.ui.adapter.CharacterAdapter
import com.example.paging3.ui.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var characteradapter: CharacterAdapter
    private val viewModel: CharacterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindViews()
    }

    private fun bindViews() {
        characteradapter = CharacterAdapter()
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = characteradapter
            setHasFixedSize(true)
        }
        lifecycleScope.launch {
            viewModel.listData.collect { pagingData ->
                Log.d("TAG", "load: $pagingData")
                characteradapter.submitData(pagingData)
            }
        }
    }
}
