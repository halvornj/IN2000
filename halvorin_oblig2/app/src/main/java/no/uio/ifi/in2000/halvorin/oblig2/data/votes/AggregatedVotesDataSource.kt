package no.uio.ifi.in2000.halvorin.oblig2.data.votes

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import no.uio.ifi.in2000.halvorin.oblig2.model.votes.DistrictVotes
import io.ktor.client.request.*
import io.ktor.http.isSuccess
import no.uio.ifi.in2000.halvorin.oblig2.model.votes.District


@Serializable
data class DistrictThreeParty(val partyId:String, val votes:Int)
@Serializable
data class DistrictThreeParties(val parties:List<DistrictThreeParty>)


class AggregatedVotesDataSource {
    suspend fun getAggregatedVotes():List<DistrictVotes>{
        val receivedVotes = mutableListOf<DistrictVotes>()

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
        val url = "https://www.uio.no/studier/emner/matnat/ifi/IN2000/v24/obligatoriske-oppgaver/district3.json"
        try{
            val response = client.get(url)
            if(response.status.isSuccess()){
                val parties :DistrictThreeParties = response.body()
                parties.parties.forEach { party ->
                    receivedVotes.add(DistrictVotes(District.DISTRICT_THREE, party.partyId, party.votes))
                }
            }else{
                throw Exception()//TODO
            }
        }catch(e:Exception){
            e.printStackTrace()
        }finally {
            client.close()
        }
        return receivedVotes
    }
}