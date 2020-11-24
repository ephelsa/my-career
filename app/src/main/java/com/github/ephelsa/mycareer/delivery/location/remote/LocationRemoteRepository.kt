package com.github.ephelsa.mycareer.delivery.location.remote

import com.github.ephelsa.mycareer.data.location.LocationRemoteDataSource
import com.github.ephelsa.mycareer.delivery.shared.mapper.toDomain
import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrapperRemoteHandler
import com.github.ephelsa.mycareer.domain.location.CountryRemote
import com.github.ephelsa.mycareer.domain.location.DepartmentRemote
import com.github.ephelsa.mycareer.domain.location.MunicipalityRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRemoteRepository @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val locationService: LocationService
) : LocationRemoteDataSource {

    private val wrapperRemoteHandler = WrapperRemoteHandler

    override suspend fun countries(): ResourceRemote<List<CountryRemote>> =
        withContext(dispatcher) {
            try {
                val responseJSON = locationService.countries()
                wrapperRemoteHandler.handleSuccess(responseJSON.toDomain())
            } catch (e: Exception) {
                wrapperRemoteHandler.handleError<List<CountryRemote>>(e)
            }
        }

    override suspend fun departmentsByCountry(countryCode: String): ResourceRemote<List<DepartmentRemote>> =
        withContext(dispatcher) {
            try {
                val responseJSON = locationService.departmentsByCountry(countryCode)
                wrapperRemoteHandler.handleSuccess(responseJSON.toDomain())
            } catch (e: Exception) {
                wrapperRemoteHandler.handleError<List<DepartmentRemote>>(e)
            }
        }

    override suspend fun municipalitiesByCountryAndDepartment(
        countryCode: String,
        departmentCode: String
    ): ResourceRemote<List<MunicipalityRemote>> = withContext(dispatcher) {
        try {
            val responseJSON = locationService
                .municipalitiesByCountryAndDepartment(countryCode, departmentCode)
            wrapperRemoteHandler.handleSuccess(responseJSON.toDomain())
        } catch (e: Exception) {
            wrapperRemoteHandler.handleError<List<MunicipalityRemote>>(e)
        }
    }
}
