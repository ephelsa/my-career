package com.github.ephelsa.mycareer.infraestructure.shared.mapper

interface DomainMappable<Domain> {
    fun toDomain(): Domain
}
