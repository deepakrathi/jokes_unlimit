package com.example.jokes.ui.jokes

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jokes.models.Joke
import com.example.jokes.databinding.ActivityMainBinding
import com.example.jokes.ui.theme.JokesTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: JokesAdapter
    private val jokesViewModel: JokesViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private var jokesList = ArrayList<Joke>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)
        initObservers()
    }

    private fun initUi() {
        adapter = JokesAdapter(jokesList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun initObservers() {
        jokesViewModel.localDbJokes.observe(this) {
            jokesList = it as ArrayList<Joke>
            initUi()
        }

        jokesViewModel.joke.observe(this) {
            adapter.addJoke(it)
            binding.recyclerView.layoutManager?.scrollToPosition(0)
        }
    }
}