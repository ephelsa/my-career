package com.github.ephelsa.mycareer.delivery.documenttype.remote

import com.github.ephelsa.mycareer.delivery.documenttype.pojo.DocumentTypeJSON
import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrappedListResponseJSON
import com.github.ephelsa.mycareer.domain.documenttype.DocumentTypeRemote
import retrofit2.http.GET

interface DocumentTypeService {
    @GET("document-type")
    suspend fun documentTypes(): WrappedListResponseJSON<DocumentTypeRemote, DocumentTypeJSON>
}
