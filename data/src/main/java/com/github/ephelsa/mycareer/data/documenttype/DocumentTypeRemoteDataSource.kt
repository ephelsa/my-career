package com.github.ephelsa.mycareer.data.documenttype

import com.github.ephelsa.mycareer.domain.documenttype.DocumentTypeRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote

interface DocumentTypeRemoteDataSource {
    suspend fun documentTypes(): ResourceRemote<List<DocumentTypeRemote>>
}
