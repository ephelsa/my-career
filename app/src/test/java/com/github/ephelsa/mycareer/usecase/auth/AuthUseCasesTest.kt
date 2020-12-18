package com.github.ephelsa.mycareer.usecase.auth

import com.github.ephelsa.mycareer.data.auth.AuthRepository
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.domain.auth.RegistryRemote
import com.github.ephelsa.mycareer.domain.shared.ErrorRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.shared.StatusRemote
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
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
        val newUser = ResourceRemote.Success(StatusRemote.Success, response)
        val useCase = RegisterAUserUseCase(repository)
        val registry: RegistryRemote = mockk()

        coEvery { repository.newUser(registry) } returns newUser

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
        val newUser = ResourceRemote.Error<AuthCredentialRemote>(StatusRemote.Error, response)
        val useCase = RegisterAUserUseCase(repository)
        val registry: RegistryRemote = mockk()

        coEvery { repository.newUser(registry) } returns newUser

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
    fun `LoginUseCase success auth with success store`() {
        val response = AuthCredentialRemote("xephelsax@gmail.com", null, "123123")
        val login = ResourceRemote.Success(StatusRemote.Success, response)
        val storeLoginUseCase = StoreSessionUseCase(repository, mockk())
        val useCase = LoginUseCase(repository, storeLoginUseCase)
        val credentials = AuthCredentialRemote("xephelsax@gmail.com", "123456")

        coEvery { repository.login(credentials) } returns login

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
    fun `LoginUseCase success auth with failed store`() {
        val response = AuthCredentialRemote("xephelsax@gmail.com", null, "123123")
        val login = ResourceRemote.Success(StatusRemote.Success, response)
        val storeLoginUseCase = StoreSessionUseCase(repository, mockk())
        val useCase = LoginUseCase(repository, storeLoginUseCase)
        val credentials = AuthCredentialRemote("xephelsax@gmail.com", "123456")

        coEvery { repository.storeSession(credentials.localTransform()) } returns ResourceLocal.Error<Unit>(
            NullPointerException()
        )
        coEvery { repository.login(credentials) } returns login

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
        val login = ResourceRemote.Error<AuthCredentialRemote>(StatusRemote.Error, response)
        val useCase = LoginUseCase(repository, mockk())
        val credentials: AuthCredentialRemote = mockk()

        coEvery { repository.login(credentials) } returns login

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
