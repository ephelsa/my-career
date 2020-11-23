package com.github.ephelsa.mycareer.usecase.location

import com.github.ephelsa.mycareer.data.location.LocationRepository

class MunicipalitiesByCountryAndDepartmentUseCase(
    private val locationRepository: LocationRepository
) {
    operator fun invoke(
        countryCode: String,
        departmentCode: String
    ) = locationRepository.municipalitiesByCountryAndDepartment(countryCode, departmentCode)
}
