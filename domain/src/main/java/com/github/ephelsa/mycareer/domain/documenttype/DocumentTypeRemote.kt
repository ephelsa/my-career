package com.github.ephelsa.mycareer.domain.documenttype

data class DocumentTypeRemote(
    val id: String,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}
