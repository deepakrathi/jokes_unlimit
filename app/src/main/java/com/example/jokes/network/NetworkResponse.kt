package com.example.jokes.network

sealed interface NetworkResponse<Success : Any>{

    /**
     * Generic network response wrapper to be used in widely to handle responses in similar fashion
     */
    data class Success<Success : Any>(val data : Success) : NetworkResponse<Success>
    data class Failure<Error : Any>(val data : Error?) : NetworkResponse<Error>

    /**
     * could be added for loading state
     */
    //object Loading : NetworkResponse<Nothing>

}
