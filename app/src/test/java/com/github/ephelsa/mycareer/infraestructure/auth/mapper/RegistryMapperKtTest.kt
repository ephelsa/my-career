package com.github.ephelsa.mycareer.infraestructure.auth.mapper

import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.domain.auth.RegistryRemote
import com.github.ephelsa.mycareer.infraestructure.auth.remote.AuthCredentialJSON
import com.github.ephelsa.mycareer.infraestructure.auth.remote.RegistryJSON
import org.junit.Assert.assertEquals
import org.junit.Test

class RegistryMapperKtTest {

    @Test
    fun `RegistryRemote toDelivery`() {
        val registryRemote = RegistryRemote(
            "Leonardo",
            "Andres",
            "Perez",
            "Castilla",
            "CC",
            "23123",
            "CO",
            "70",
            "001",
            4,
            2,
            "Liceo Panamericano Campestre",
            "xephelsax@gmail.com",
            "TopSecretPassword"
        )

        val got = registryRemote.toDelivery()
        val want = RegistryJSON(
            "Leonardo",
            "Andres",
            "Perez",
            "Castilla",
            "CC",
            "23123",
            "CO",
            "70",
            "001",
            4,
            2,
            "Liceo Panamericano Campestre",
            "xephelsax@gmail.com",
            "TopSecretPassword"
        )

        assertEquals(want, got)
    }

    @Test
    fun `AuthCredentialRemote toDelivery`() {
        val credentialRemote = AuthCredentialRemote("xephelsax@gmail.com", "TopSecret")

        val want = AuthCredentialJSON("xephelsax@gmail.com", "TopSecret")
        val got = credentialRemote.toDelivery()

        assertEquals(want, got)
    }
}