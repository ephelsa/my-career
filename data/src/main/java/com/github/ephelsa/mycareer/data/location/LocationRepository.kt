package com.github.ephelsa.mycareer.data.location

import com.github.ephelsa.mycareer.domain.location.CountryRemote
import com.github.ephelsa.mycareer.domain.location.DepartmentRemote
import com.github.ephelsa.mycareer.domain.location.MunicipalityRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocationRepository(
    private val locationRemoteDataSource: LocationRemoteDataSource
) {
    fun countries(): Flow<ResourceRemote<List<CountryRemote>>> = flow {
        emit(ResourceRemote.Loading())
        emit(locationRemoteDataSource.countries())
    }

    fun departmentsByCountry(countryCode: String): Flow<ResourceRemote<List<DepartmentRemote>>> = flow {
        emit(ResourceRemote.Loading())
        emit(locationRemoteDataSource.departmentsByCountry(countryCode))
    }

    fun municipalitiesByCountryAndDepartment(
        countryCode: String,
        departmentCode: String
    ): Flow<ResourceRemote<List<MunicipalityRemote>>> = flow {
        emit(ResourceRemote.Loading())
        emit(locationRemoteDataSource.municipalitiesByCountryAndDepartment(countryCode, departmentCode))
    }
}
