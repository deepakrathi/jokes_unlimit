package com.example.jokes.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Extension function to launch coroutine
 */
fun ViewModel.launch(dispatcher: CoroutineDispatcher = Dispatchers.IO, block : suspend () -> Unit) {
    viewModelScope.launch(dispatcher) {
        block()
    }
}