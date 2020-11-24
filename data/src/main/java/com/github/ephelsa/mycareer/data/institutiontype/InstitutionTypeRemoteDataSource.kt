package com.github.ephelsa.mycareer.data.institutiontype

import com.github.ephelsa.mycareer.domain.institutiontype.InstitutionTypeRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote

interface InstitutionTypeRemoteDataSource {
    suspend fun institutionTypes(): ResourceRemote<List<InstitutionTypeRemote>>
}
