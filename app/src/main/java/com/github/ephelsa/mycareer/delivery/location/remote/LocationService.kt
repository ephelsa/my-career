package com.github.ephelsa.mycareer.delivery.location.remote

import com.github.ephelsa.mycareer.delivery.location.pojo.CountryJSON
import com.github.ephelsa.mycareer.delivery.location.pojo.DepartmentJSON
import com.github.ephelsa.mycareer.delivery.location.pojo.MunicipalityJSON
import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrappedListResponseJSON
import com.github.ephelsa.mycareer.domain.location.CountryRemote
import com.github.ephelsa.mycareer.domain.location.DepartmentRemote
import com.github.ephelsa.mycareer.domain.location.MunicipalityRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationService {
    @GET("/location/country")
    suspend fun countries(): WrappedListResponseJSON<CountryRemote, CountryJSON>

    @GET("/location/country/{countryCode}/department")
    suspend fun departmentsByCountry(
        @Path("countryCode") countryCode: String
    ): WrappedListResponseJSON<DepartmentRemote, DepartmentJSON>

    @GET("/location/country/{countryCode}/department/{departmentCode}/municipality")
    suspend fun municipalitiesByCountryAndDepartment(
        @Path("countryCode") countryCode: String,
        @Path("departmentCode") departmentCode: String,
    ): WrappedListResponseJSON<MunicipalityRemote, MunicipalityJSON>
}
