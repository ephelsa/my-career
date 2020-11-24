package com.github.ephelsa.mycareer.data.documenttype

class DocumentTypeRepository(
    private val documentTypeRemoteDataSource: DocumentTypeRemoteDataSource
) {
    suspend fun documentTypes() = documentTypeRemoteDataSource.documentTypes()
}
