package com.github.ephelsa.mycareer.usecase.documenttype

import com.github.ephelsa.mycareer.data.documenttype.DocumentTypeRepository
import com.github.ephelsa.mycareer.domain.documenttype.DocumentTypeRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DocumentTypesUseCase(
    private val documentTypeRepository: DocumentTypeRepository
) {
    operator fun invoke(): Flow<ResourceRemote<List<DocumentTypeRemote>>> = flow {
        emit(ResourceRemote.Loading())
        emit(documentTypeRepository.documentTypes())
    }
}
