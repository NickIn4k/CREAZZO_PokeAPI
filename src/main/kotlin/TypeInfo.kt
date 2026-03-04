package org.example

data class TypeInfo(
    val slot: Int,
    val type: Type
){
    override fun toString(): String {
        return type.toString()
    }
}
