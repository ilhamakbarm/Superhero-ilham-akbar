package com.ilham.superhero

import retrofit2.Call
import retrofit2.http.GET

interface SuperheroApiService {
    @GET("498")
    fun getSuperhero(): Call<Superhero>
}
