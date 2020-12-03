package com.github.ephelsa.mycareer.delivery.studylevel.pojo

import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.studylevel.StudyLevelRemote
import com.google.gson.annotations.SerializedName

data class StudyLevelJSON(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
) : DomainMappable<StudyLevelRemote> {
    override fun toDomain(): StudyLevelRemote = StudyLevelRemote(id, name)
}
