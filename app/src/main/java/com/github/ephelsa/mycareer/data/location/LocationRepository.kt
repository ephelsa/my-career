package com.github.ephelsa.mycareer.data.location

import com.github.ephelsa.mycareer.domain.location.CountryRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import kotlinx.coroutines.flow.Flow

class LocationRepository(
    private val locationRemoteDataSource: LocationRemoteDataSource
) {
    fun countries(): Flow<ResourceRemote<List<CountryRemote>>> = locationRemoteDataSource.countries()

    fun departmentsByCountry(countryCode: String) =
        locationRemoteDataSource.departmentsByCountry(countryCode)

    fun municipalitiesByCountryAndDepartment(
        countryCode: String,
        departmentCode: String
    ) = locationRemoteDataSource.municipalitiesByCountryAndDepartment(countryCode, departmentCode)
}
