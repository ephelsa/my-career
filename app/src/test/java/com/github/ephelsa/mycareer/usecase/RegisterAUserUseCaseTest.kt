package com.github.ephelsa.mycareer.usecase

import com.github.ephelsa.mycareer.data.auth.AuthRemoteDataSource
import com.github.ephelsa.mycareer.data.auth.AuthRepository
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.domain.auth.RegistryRemote
import com.github.ephelsa.mycareer.domain.shared.ErrorRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.shared.StatusRemote
import com.github.ephelsa.mycareer.usecase.auth.RegisterAUserUseCase
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class RegisterAUserUseCaseTest {
    private val testDispatcher = TestCoroutineDispatcher()

    @Test
    fun `success remote response`() {
        val response = AuthCredentialRemote("xephelsax@gmail.com", null)
        val repo = object : AuthRemoteDataSource {
            override fun newUser(registryRemote: RegistryRemote): Flow<ResourceRemote<AuthCredentialRemote>> =
                flow {
                    emit(ResourceRemote.Loading())
                    emit(ResourceRemote.Success(StatusRemote.Success, response))
                    emit(ResourceRemote.Complete())
                }
        }
        val authRepository = AuthRepository(repo)
        val useCase = RegisterAUserUseCase(authRepository)
        val registry: RegistryRemote = mockk()

        runBlockingTest(testDispatcher) {
            val data = useCase(registry)

            data.collect {
                when (it) {
                    is ResourceRemote.Loading -> assert(true)
                    is ResourceRemote.Success -> {
                        assertEquals(StatusRemote.Success, it.status)
                        assertEquals(response, it.data)
                    }
                    is ResourceRemote.Error -> assert(false)
                    is ResourceRemote.Complete -> assert(true)
                }
            }
        }
    }

    @Test
    fun `error remote response`() {
        val response = ErrorRemote("Something went wrong", "Dude, your name is Doodoo")
        val repo = object : AuthRemoteDataSource {
            override fun newUser(registryRemote: RegistryRemote): Flow<ResourceRemote<AuthCredentialRemote>> =
                flow {
                    emit(ResourceRemote.Loading())
                    emit(ResourceRemote.Error(StatusRemote.Error, response))
                    emit(ResourceRemote.Complete())
                }
        }
        val authRepository = AuthRepository(repo)
        val useCase = RegisterAUserUseCase(authRepository)
        val registry: RegistryRemote = mockk()

        runBlockingTest(testDispatcher) {
            val data = useCase(registry)

            data.collect {
                when (it) {
                    is ResourceRemote.Loading -> assert(true)
                    is ResourceRemote.Success -> assert(false)
                    is ResourceRemote.Error -> {
                        assertEquals(StatusRemote.Error, it.status)
                        assertEquals(response, it.error)
                    }
                    is ResourceRemote.Complete -> assert(true)
                }
            }
        }
    }
}