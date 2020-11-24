package com.github.ephelsa.mycareer.data.institutiontype

class InstitutionTypeRepository(
    private val institutionTypeRemoteDataSource: InstitutionTypeRemoteDataSource
) {
    suspend fun institutionTypes() = institutionTypeRemoteDataSource.institutionTypes()
}
