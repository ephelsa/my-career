package com.github.ephelsa.mycareer.infraestructure.shared.mapper

import com.github.ephelsa.mycareer.TestSuiteSimple
import com.github.ephelsa.mycareer.domain.shared.ErrorRemote
import com.github.ephelsa.mycareer.domain.shared.StatusRemote
import com.github.ephelsa.mycareer.domain.shared.WrappedRemote
import com.github.ephelsa.mycareer.infraestructure.shared.remote.GsonBuild
import com.github.ephelsa.mycareer.infraestructure.shared.remote.json.ErrorResponseJSON
import com.github.ephelsa.mycareer.infraestructure.shared.remote.json.StatusResponseJSON
import com.github.ephelsa.mycareer.infraestructure.shared.remote.json.WrappedListResponseJSON
import com.github.ephelsa.mycareer.infraestructure.shared.remote.json.WrappedResponseJSON
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import org.junit.Assert.assertEquals
import org.junit.Test

class ResponseToRemoteMapperKtTest {

    private data class SomethingRemote(val name: String)
    private data class SomethingJson(
        @SerializedName("name") val name: String
    ) : DomainMappable<SomethingRemote> {
        override fun toDomain() = SomethingRemote(name)
    }

    @Test
    fun `ErrorResponseJSON json parser`() {
        val raw = """
            {
                "message": "Something went wrong",
                "details": "Oh, dude..."
            }
        """.trimIndent()

        val got = GsonBuild.gson.fromJson(raw, ErrorResponseJSON::class.java)
        val want = ErrorResponseJSON("Something went wrong", "Oh, dude...")

        assertEquals(want, got)
    }

    @Test
    fun `toDomain ErrorResponseJSON`() {
        val errorResponseJSON = ErrorResponseJSON(
            message = "An error message",
            details = "Reasons why"
        )

        val got = errorResponseJSON.toDomain()
        val want = ErrorRemote(
            message = "An error message",
            details = "Reasons why"
        )

        assertEquals(want, got)
    }

    @Test
    fun `toDomain StatusResponseJSON`() {
        val tests = listOf(
            TestSuiteSimple(
                "Error JSON to Error remote",
                StatusResponseJSON.Error.toDomain(),
                StatusRemote.Error
            ),
            TestSuiteSimple(
                "Success JSON to Success remote",
                StatusResponseJSON.Success.toDomain(),
                StatusRemote.Success
            ),
        )

        for (tt in tests) {
            assertEquals(tt.description, tt.want, tt.got)
        }
    }

    @Test
    fun `WrappedResponseJSON json parser`() {
        val type = object : TypeToken<WrappedResponseJSON<SomethingRemote, SomethingJson>>() {}.type

        val tests = listOf(
            TestSuiteSimple(
                "with result and success",
                GsonBuild.gson.fromJson(
                    """{ "status": "success", "result": { "name": "leonardo" } }""",
                    type
                ),
                WrappedResponseJSON(
                    StatusResponseJSON.Success,
                    result = SomethingJson("leonardo"),
                    null
                )
            ),
            TestSuiteSimple(
                "with result and error",
                GsonBuild.gson.fromJson(
                    """{ "status": "error", "error": { "message": "Bad news", "details": "Oh god.." } }""",
                    type
                ),
                WrappedResponseJSON(
                    StatusResponseJSON.Error,
                    result = null,
                    error = ErrorResponseJSON("Bad news", "Oh god..")
                )
            )
        )

        for (tt in tests) {
            assertEquals(tt.description, tt.want, tt.got)
        }
    }

    @Test
    fun `toDomain WrappedResponseJSON`() {
        val tests = listOf(
            TestSuiteSimple(
                "result string",
                WrappedResponseJSON<SomethingRemote, SomethingJson>(
                    StatusResponseJSON.Success,
                    SomethingJson("Leonardo"),
                    null
                ).toDomain(),
                WrappedRemote(StatusRemote.Success, SomethingRemote("Leonardo"), null)
            ),
            TestSuiteSimple<Any>(
                "error",
                WrappedResponseJSON<SomethingRemote, SomethingJson>(
                    StatusResponseJSON.Error,
                    null,
                    ErrorResponseJSON("Unexpected error", "Bad news")
                ).toDomain(),
                WrappedRemote(
                    StatusRemote.Error,
                    null,
                    ErrorRemote("Unexpected error", "Bad news")
                )
            )
        )

        for (tt in tests) {
            assertEquals(tt.description, tt.want, tt.got)
        }
    }

    @Test
    fun `WrappedListResponseJSON json parser`() {
        val type = object : TypeToken<WrappedListResponseJSON<SomethingRemote, SomethingJson>>() {}.type
        val tests = listOf(
            TestSuiteSimple(
                "with result and success",
                GsonBuild.gson.fromJson(
                    """{ "status": "success", "result": [ { "name": "leonardo" }, { "name": "karen" } ] }""",
                    type
                ),
                WrappedListResponseJSON(
                    StatusResponseJSON.Success,
                    result = listOf(SomethingJson("leonardo"), SomethingJson("karen")),
                    null
                )
            ),
            TestSuiteSimple(
                "with result and error",
                GsonBuild.gson.fromJson(
                    """{ "status": "error", "error": { "message": "Bad news", "details": "Oh god.." } }""",
                    type
                ),
                WrappedListResponseJSON(
                    StatusResponseJSON.Error,
                    result = null,
                    error = ErrorResponseJSON("Bad news", "Oh god..")
                )
            )
        )

        for (tt in tests) {
            assertEquals(tt.description, tt.want, tt.got)
        }
    }

    @Test
    fun `toDomain WrappedListResponseJSON`() {
        val tests = listOf(
            TestSuiteSimple(
                "result string",
                WrappedListResponseJSON<SomethingRemote, SomethingJson>(
                    StatusResponseJSON.Success,
                    listOf(SomethingJson("Leonardo")),
                    null
                ).toDomain(),
                WrappedRemote(StatusRemote.Success, listOf(SomethingRemote("Leonardo")), null)
            ),
            TestSuiteSimple<Any>(
                "error",
                WrappedListResponseJSON<SomethingRemote, SomethingJson>(
                    StatusResponseJSON.Error,
                    null,
                    ErrorResponseJSON("Unexpected error", "Bad news")
                ).toDomain(),
                WrappedRemote(
                    StatusRemote.Error,
                    null,
                    ErrorRemote("Unexpected error", "Bad news")
                )
            )
        )

        for (tt in tests) {
            assertEquals(tt.description, tt.want, tt.got)
        }
    }
}