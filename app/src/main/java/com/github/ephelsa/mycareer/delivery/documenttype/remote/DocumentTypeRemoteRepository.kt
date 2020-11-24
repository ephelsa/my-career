package com.github.ephelsa.mycareer.delivery.documenttype.remote

import com.github.ephelsa.mycareer.data.documenttype.DocumentTypeRemoteDataSource
import com.github.ephelsa.mycareer.delivery.shared.mapper.toDomain
import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrapperRemoteHandler
import com.github.ephelsa.mycareer.domain.documenttype.DocumentTypeRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DocumentTypeRemoteRepository @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val documentTypeService: DocumentTypeService
) : DocumentTypeRemoteDataSource {
    private val wrapperRemoteHandler = WrapperRemoteHandler

    override suspend fun documentTypes(): ResourceRemote<List<DocumentTypeRemote>> =
        withContext(dispatcher) {
            try {
                val responseJSON = documentTypeService.documentTypes()
                wrapperRemoteHandler.handleSuccess(responseJSON.toDomain())
            } catch (e: Exception) {
                wrapperRemoteHandler.handleError<List<DocumentTypeRemote>>(e)
            }
        }
}
