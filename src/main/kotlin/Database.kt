package org.example

import java.sql.Connection
import java.sql.DriverManager

object Database {
    private const val URL = "jdbc:sqlite:src/main/resources/pokemon.db"

    fun getConnection(): Connection {
        return DriverManager.getConnection(URL)
    }

    fun savePokemon(pokemon: Pokemon?) {
        if (pokemon == null) {
            println("Pokemon nullo, impossibile salvare.")
            return
        }

        val connection = getConnection()

        try {
            val insertQuery = """
                INSERT OR REPLACE INTO pokemon (id, name, height, weight)
                VALUES (?, ?, ?, ?)
            """.trimIndent()

            val insertStatement = connection.prepareStatement(insertQuery)

            insertStatement.setInt(1, pokemon.getId())
            insertStatement.setString(2, pokemon.getName())
            insertStatement.setInt(3, pokemon.getHeight())
            insertStatement.setInt(4, pokemon.getWeight())

            insertStatement.executeUpdate()
            insertStatement.close()

            saveType(pokemon, connection)

        } finally {
            connection.close()
        }
    }

    private fun saveType(pokemon: Pokemon, connection: Connection) {

        val insertQuery2 = """
            INSERT OR REPLACE INTO types (id, name, url, idPokemon)
            VALUES (?, ?, ?, ?)
        """.trimIndent()

        val stmt = connection.prepareStatement(insertQuery2)

        for (ti in pokemon.getTypes()) {
            stmt.setInt(1, ti.slot)
            stmt.setString(2, ti.type.name)
            stmt.setString(3, ti.type.url)
            stmt.setInt(4, pokemon.getId())

            stmt.executeUpdate()
        }

        stmt.close()
    }
}