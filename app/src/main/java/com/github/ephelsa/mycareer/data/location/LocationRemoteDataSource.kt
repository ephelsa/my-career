package com.github.ephelsa.mycareer.data.location

import com.github.ephelsa.mycareer.domain.location.CountryRemote
import com.github.ephelsa.mycareer.domain.location.DepartmentRemote
import com.github.ephelsa.mycareer.domain.location.MunicipalityRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import kotlinx.coroutines.flow.Flow

interface LocationRemoteDataSource {
    fun countries(): Flow<ResourceRemote<List<CountryRemote>>>

    fun departmentsByCountry(countryCode: String): Flow<ResourceRemote<List<DepartmentRemote>>>

    fun municipalitiesByCountryAndDepartment(
        countryCode: String,
        departmentCode: String
    ): Flow<ResourceRemote<List<MunicipalityRemote>>>
}
