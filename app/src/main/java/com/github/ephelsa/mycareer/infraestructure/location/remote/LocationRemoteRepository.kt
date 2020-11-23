package com.github.ephelsa.mycareer.infraestructure.location.remote

import com.github.ephelsa.mycareer.data.location.LocationRemoteDataSource
import com.github.ephelsa.mycareer.domain.location.CountryRemote
import com.github.ephelsa.mycareer.domain.location.DepartmentRemote
import com.github.ephelsa.mycareer.domain.location.MunicipalityRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.infraestructure.shared.mapper.toDomain
import com.github.ephelsa.mycareer.infraestructure.shared.remote.json.WrapperRemoteHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocationRemoteRepository(
    private val locationService: LocationService
) : LocationRemoteDataSource {

    private val wrapperRemoteHandler = WrapperRemoteHandler

    override fun countries(): Flow<ResourceRemote<List<CountryRemote>>> = flow {
        emit(ResourceRemote.Loading())
        try {
            val response = locationService.countries().toDomain()
            emit(wrapperRemoteHandler.handleSuccess(response))
        } catch (e: Exception) {
            emit(wrapperRemoteHandler.handleError<List<CountryRemote>>(e))
        }
    }

    override fun departmentsByCountry(countryCode: String): Flow<ResourceRemote<List<DepartmentRemote>>> =
        flow {
            emit(ResourceRemote.Loading())
            try {
                val response = locationService.departmentsByCountry(countryCode).toDomain()
                emit(wrapperRemoteHandler.handleSuccess(response))
            } catch (e: Exception) {
                emit(wrapperRemoteHandler.handleError<List<DepartmentRemote>>(e))
            }
        }

    override fun municipalitiesByCountryAndDepartment(
        countryCode: String,
        departmentCode: String
    ): Flow<ResourceRemote<List<MunicipalityRemote>>> = flow {
        emit(ResourceRemote.Loading())
        try {
            val response =
                locationService.municipalitiesByCountryAndDepartment(countryCode, departmentCode)
                    .toDomain()
            emit(wrapperRemoteHandler.handleSuccess(response))
        } catch (e: Exception) {
            emit(wrapperRemoteHandler.handleError<List<MunicipalityRemote>>(e))
        }
    }
}
