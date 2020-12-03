package com.github.ephelsa.mycareer.delivery.institutiontype.remote

import com.github.ephelsa.mycareer.delivery.institutiontype.pojo.InstitutionTypeJSON
import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrappedListResponseJSON
import com.github.ephelsa.mycareer.domain.institutiontype.InstitutionTypeRemote
import retrofit2.http.GET

interface InstitutionTypeService {
    @GET("institution-type")
    suspend fun institutionTypes(): WrappedListResponseJSON<InstitutionTypeRemote, InstitutionTypeJSON>
}
