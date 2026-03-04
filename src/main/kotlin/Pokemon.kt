package org.example

data class Pokemon (
    private var id: Int,
    private var name: String,
    private var height: Int,
    private var weight: Int,
    private var types: List<TypeInfo>
) {
    fun getId(): Int = id

    fun getName(): String = name

    fun getHeight(): Int = height

    fun getWeight(): Int = weight

    fun getTypes(): String = types.joinToString(" ")

    override fun toString() : String {
        var msg = ""
        for (type in types)
            msg += "$type "

        return "ID: $id\nNome: $name\nAltezza: $height\nPeso: $weight\nTipi: $msg"
    }
}