package com.github.ephelsa.mycareer.infraestructure.location.remote

import com.github.ephelsa.mycareer.domain.location.CountryRemote
import com.github.ephelsa.mycareer.domain.location.DepartmentRemote
import com.github.ephelsa.mycareer.domain.location.MunicipalityRemote
import com.github.ephelsa.mycareer.infraestructure.auth.remote.MunicipalityJSON
import com.github.ephelsa.mycareer.infraestructure.shared.remote.json.WrappedListResponseJSON
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationService {
    @GET("/location/country")
    fun countries(): WrappedListResponseJSON<CountryRemote, CountryJSON>

    @GET("/location/{countryCode}/department")
    fun departmentsByCountry(
        @Path("countryCode") countryCode: String
    ): WrappedListResponseJSON<DepartmentRemote, DepartmentJSON>

    @GET("/location/{countryCode}/department/{departmentCode}/municipality")
    fun municipalitiesByCountryAndDepartment(
        @Path("countryCode") countryCode: String,
        @Path("departmentCode") departmentCode: String,
    ): WrappedListResponseJSON<MunicipalityRemote, MunicipalityJSON>
}
