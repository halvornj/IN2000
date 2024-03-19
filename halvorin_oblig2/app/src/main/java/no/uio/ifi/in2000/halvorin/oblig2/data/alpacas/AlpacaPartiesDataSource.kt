package no.uio.ifi.in2000.halvorin.oblig2.data.alpacas

import android.util.Log
import no.uio.ifi.in2000.halvorin.oblig2.model.alpacas.PartyInfo
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.reflect.TypeInfo
import kotlinx.serialization.json.Json
import no.uio.ifi.in2000.halvorin.oblig2.model.alpacas.Parties

class AlpacaPartiesDataSource {
    suspend fun getAlpacaPartiesData(): List<PartyInfo> {

        val TAG = "AlpacaPartiesDataSource"

        var partyList: List<PartyInfo> = mutableListOf()

        val client = HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                    encodeDefaults = true
                })
            }
        }
        try {
            val response: HttpResponse =
                client.get("https://www.uio.no/studier/emner/matnat/ifi/IN2000/v24/obligatoriske-oppgaver/alpacaparties.json")

            //first, check for error codes. A request can be successful, with an unsuccessful body
            if (response.status.isSuccess()) {
                val parties: Parties = response.body()
                partyList = parties.parties
            } else if (response.status.value in 500..599) {    //there's gotta be a better way to do this
                throw ServerResponseException(response, "error on server side")
            } else if (response.status.value in 400..499) {
                throw ClientRequestException(response, "client-error upon request")
            }


        } catch (e: Exception) {//TODO find more specific exception, although most errors show up as status codes not exceptions?
            //the throws above are basically useless until i find a more practical catch, but hey they look nice at least
            //https://ktor.io/docs/client-retry.html#conditions
            e.printStackTrace()

        } finally {
            client.close()
        }

        return partyList
    }
}