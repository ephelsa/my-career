package com.github.ephelsa.mycareer.usecase.location

import com.github.ephelsa.mycareer.data.location.LocationRepository
import com.github.ephelsa.mycareer.domain.location.CountryRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import kotlinx.coroutines.flow.Flow

class CountriesUseCase(private val locationRepository: LocationRepository) {
    operator fun invoke(): Flow<ResourceRemote<List<CountryRemote>>> = locationRepository.countries()
}
