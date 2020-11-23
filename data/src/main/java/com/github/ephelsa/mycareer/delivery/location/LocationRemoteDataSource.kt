package com.github.ephelsa.mycareer.delivery.location

import com.github.ephelsa.mycareer.domain.location.CountryRemote
import com.github.ephelsa.mycareer.domain.location.DepartmentRemote
import com.github.ephelsa.mycareer.domain.location.MunicipalityRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote

interface LocationRemoteDataSource {
    suspend fun countries(): ResourceRemote<List<CountryRemote>>

    suspend fun departmentsByCountry(countryCode: String): ResourceRemote<List<DepartmentRemote>>

    suspend fun municipalitiesByCountryAndDepartment(
        countryCode: String,
        departmentCode: String
    ): ResourceRemote<List<MunicipalityRemote>>
}
