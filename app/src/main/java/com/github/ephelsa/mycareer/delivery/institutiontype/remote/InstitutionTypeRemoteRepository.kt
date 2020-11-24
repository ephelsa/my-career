package com.github.ephelsa.mycareer.delivery.institutiontype.remote

import com.github.ephelsa.mycareer.data.institutiontype.InstitutionTypeRemoteDataSource
import com.github.ephelsa.mycareer.delivery.shared.mapper.toDomain
import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrapperRemoteHandler
import com.github.ephelsa.mycareer.domain.institutiontype.InstitutionTypeRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InstitutionTypeRemoteRepository @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val institutionTypeService: InstitutionTypeService
) : InstitutionTypeRemoteDataSource {
    override suspend fun institutionTypes(): ResourceRemote<List<InstitutionTypeRemote>> =
        withContext(dispatcher) {
            try {
                val responseJSON = institutionTypeService.institutionTypes()
                WrapperRemoteHandler.handleSuccess(responseJSON.toDomain())
            } catch (e: Exception) {
                WrapperRemoteHandler.handleError<List<InstitutionTypeRemote>>(e)
            }
        }
}
