package org.example

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    // Indica una richiesta GET HTTP
    @GET("pokemon/{nome}")

    // Sostituisci il nome con il valore passato
    fun getPokemon(@Path("nome") nome: String): Call<Pokemon>
}