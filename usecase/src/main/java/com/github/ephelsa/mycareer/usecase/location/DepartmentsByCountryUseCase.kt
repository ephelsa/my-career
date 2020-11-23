package com.github.ephelsa.mycareer.usecase.location

import com.github.ephelsa.mycareer.delivery.location.LocationRepository

class DepartmentsByCountryUseCase(
    private val locationRepository: LocationRepository
) {
    operator fun invoke(countryCode: String) = locationRepository.departmentsByCountry(countryCode)
}
