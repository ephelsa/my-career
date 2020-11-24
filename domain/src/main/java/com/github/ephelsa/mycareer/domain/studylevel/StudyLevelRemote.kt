package com.github.ephelsa.mycareer.domain.studylevel

data class StudyLevelRemote(
    val id: Int,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}
