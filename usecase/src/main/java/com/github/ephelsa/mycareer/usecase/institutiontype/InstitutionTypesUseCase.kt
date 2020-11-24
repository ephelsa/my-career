package com.github.ephelsa.mycareer.usecase.institutiontype

import com.github.ephelsa.mycareer.data.institutiontype.InstitutionTypeRepository
import com.github.ephelsa.mycareer.domain.institutiontype.InstitutionTypeRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InstitutionTypesUseCase(
    private val institutionTypeRepository: InstitutionTypeRepository
) {
    operator fun invoke(): Flow<ResourceRemote<List<InstitutionTypeRemote>>> = flow {
        emit(ResourceRemote.Loading())
        emit(institutionTypeRepository.institutionTypes())
    }
}
