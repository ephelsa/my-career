package com.github.ephelsa.mycareer.delivery.shared.mapper

interface DomainMappable<Domain> {
    fun toDomain(): Domain
}
