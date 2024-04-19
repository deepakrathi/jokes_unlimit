package com.example.jokes.ui.jokes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jokes.models.Joke
import com.example.jokes.databinding.ItemJokeBinding
import com.example.jokes.extensions.convertTimeStamp


class JokesAdapter(list: MutableList<Joke>) : RecyclerView.Adapter<JokesAdapter.ViewHolder>() {

    private var jokesList = ArrayList<Joke>()

    // Hard-coded the length for now, could be BE driven as well
    private val maxSize = 10

    init {
        this.jokesList = list as ArrayList<Joke>
    }

    fun addJoke(joke: Joke) {
        jokesList.add(0, joke)
        notifyItemInserted(0)
        if (jokesList.size == maxSize + 1) {
            jokesList.removeLast()
            notifyItemRemoved(jokesList.size)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemJokeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return jokesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(jokesList[position])
    }

    inner class ViewHolder(private val binding: ItemJokeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Joke) {
            binding.textView.text = item.joke
            binding.timestamp.text = item.timestamp.convertTimeStamp()
        }
    }
}
