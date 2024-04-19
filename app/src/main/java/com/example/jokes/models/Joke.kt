package com.example.jokes.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Jokes related data classes
 */
data class Joke(val joke: String, var timestamp: Long = System.currentTimeMillis()) {

    fun toPeristedJoke(): PersistedJoke {
        return PersistedJoke(joke = joke, timestamp = timestamp)
    }

}

@Entity
data class PersistedJoke(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo val joke: String,
    @ColumnInfo val timestamp: Long
) {
    fun toJoke(): Joke {
        return Joke(joke = joke, timestamp = timestamp)
    }
}
