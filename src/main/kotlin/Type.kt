package org.example

data class Type(
    val name: String,
    val url: String
){
    override fun toString() : String {
        return name
    }
}
