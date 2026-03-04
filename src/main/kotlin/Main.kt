package org.example

import java.sql.Connection
import java.sql.DriverManager

fun main() {
    val api = RetrofitClient.retrofit.create(PokemonApi::class.java)

    print("Inserisci il nome del Pokemon: ")
    val input = readlnOrNull()
    val response = api.getPokemon("$input").execute().body()

    println(response.toString())

    Database.savePokemon(response)
}