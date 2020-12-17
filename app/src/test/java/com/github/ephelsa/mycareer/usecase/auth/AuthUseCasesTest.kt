package com.github.ephelsa.mycareer.usecase.auth

import com.github.ephelsa.mycareer.data.auth.AuthRepository
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.domain.auth.RegistryRemote
import com.github.ephelsa.mycareer.domain.shared.ErrorRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.shared.StatusRemote
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class AuthUseCasesTest {
    private lateinit var testDispatcher: TestCoroutineDispatcher
    private val repository: AuthRepository = mockk()

    @Before
    fun setup() {
        testDispatcher = TestCoroutineDispatcher()
    }

    @Test
    fun `RegisterAUserUseCase success remote response`() {
        val response = AuthCredentialRemote("xephelsax@gmail.com", null)
        val newUser = flow {
            emit(ResourceRemote.Loading())
            emit(ResourceRemote.Success(StatusRemote.Success, response))
            emit(ResourceRemote.Complete())
        }
        val useCase = RegisterAUserUseCase(repository)
        val registry: RegistryRemote = mockk()

        every { repository.newUser(registry) } returns newUser

        runBlockingTest(testDispatcher) {
            useCase(registry).collect {
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
    fun `RegisterAUserUseCase error remote response`() {
        val response = ErrorRemote("Something went wrong", "Dude, your name is Doodoo")
        val newUser = flow<ResourceRemote<AuthCredentialRemote>> {
            emit(ResourceRemote.Loading())
            emit(ResourceRemote.Error(StatusRemote.Error, response))
            emit(ResourceRemote.Complete())
        }
        val useCase = RegisterAUserUseCase(repository)
        val registry: RegistryRemote = mockk()

        every { repository.newUser(registry) } returns newUser

        runBlockingTest(testDispatcher) {
            useCase(registry).collect {
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

    @Test
    fun `LoginUseCase success auth`() {
        val response = AuthCredentialRemote("xephelsax@gmail.com", null)
        val login = flow {
            emit(ResourceRemote.Loading())
            emit(ResourceRemote.Success(StatusRemote.Success, response))
            emit(ResourceRemote.Complete())
        }
        val useCase = LoginUseCase(repository)
        val credentials: AuthCredentialRemote = mockk()

        every { repository.login(credentials) } returns login

        runBlockingTest(testDispatcher) {
            useCase(credentials).collect {
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
    fun `LoginUseCase invalid credentials`() {
        val response = ErrorRemote("Something wrong", "Your email doesn't exists")
        val login = flow<ResourceRemote<AuthCredentialRemote>> {
            emit(ResourceRemote.Loading())
            emit(ResourceRemote.Error(StatusRemote.Error, response))
            emit(ResourceRemote.Complete())
        }
        val useCase = LoginUseCase(repository)
        val credentials: AuthCredentialRemote = mockk()

        every { repository.login(credentials) } returns login

        runBlockingTest(testDispatcher) {
            useCase(credentials).collect {
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
