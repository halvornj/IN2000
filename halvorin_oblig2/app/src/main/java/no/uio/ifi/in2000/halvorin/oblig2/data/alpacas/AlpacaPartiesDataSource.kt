package no.uio.ifi.in2000.halvorin.oblig2.data.alpacas

import no.uio.ifi.in2000.halvorin.oblig2.model.alpacas.PartyInfo
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse

suspend fun getAlpacaPartiesData():List<PartyInfo>{

    val client = HttpClient(Android)
    val parties :List<PartyInfo> = mutableListOf()
    try {
        val response: HttpResponse = client.get("https://www.uio.no/studier/emner/matnat/ifi/IN2000/v24/obligatoriske-oppgaver/alpacaparties.json")
        //TODO deserialize

    }catch(e:Exception){//TODO find more spesific exception, although most errors show up as status codes not exceptions?
        //https://ktor.io/docs/client-retry.html#conditions

    }


    //TODO remove, just to make linter happy
    return emptyList()
}