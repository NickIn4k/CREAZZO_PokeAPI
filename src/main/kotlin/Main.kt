package org.example

import java.sql.Connection
import java.sql.DriverManager

fun main() {
    val api = RetrofitClient.retrofit.create(PokemonApi::class.java)

    print("Inserisci il nome del Pokemon: ")
    val input = readLine()
    val response = api.getPokemon("$input").execute().body()

    println(response.toString())

    savePokemon(response)

}

fun getConnection(): Connection {
    val url = "jdbc:sqlite:src/main/resources/pokemon.db"
    return DriverManager.getConnection(url)
}

fun savePokemon(pokemon: Pokemon?) {
    val query = """
        INSERT OR REPLACE INTO pokemon (id, name, height, weight)
        VALUES (?, ?, ?, ?)
    """.trimIndent()

    val connection = getConnection()
    val statement = connection.prepareStatement(query)

    statement.setInt(1, pokemon!!.getId())
    statement.setString(2, pokemon.getName())
    statement.setInt(3, pokemon.getHeight())
    statement.setInt(4, pokemon.getWeight())

    statement.executeUpdate()

    statement.close()
    connection.close()
}