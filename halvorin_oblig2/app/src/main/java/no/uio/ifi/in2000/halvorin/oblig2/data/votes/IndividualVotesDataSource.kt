package no.uio.ifi.in2000.halvorin.oblig2.data.votes

import android.net.http.NetworkException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.request.*
import io.ktor.http.isSuccess
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import no.uio.ifi.in2000.halvorin.oblig2.model.votes.District
import no.uio.ifi.in2000.halvorin.oblig2.model.votes.DistrictVotes
import java.security.InvalidParameterException

@Serializable
class Vote(val id:String)

class IndividualVotesDataSource {
    suspend fun getVotesDistrict(district:District):List<DistrictVotes>{
        val TAG = "IndividualVotesDataSource"

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
            //assuming it's only gonna be called with district one or two
            val url:String = when (district) {
                District.DISTRICT_ONE -> {
                    "https://www.uio.no/studier/emner/matnat/ifi/IN2000/v24/obligatoriske-oppgaver/district1.json"
                }
                District.DISTRICT_TWO -> {"https://www.uio.no/studier/emner/matnat/ifi/IN2000/v24/obligatoriske-oppgaver/district2.json"}
                else -> {throw InvalidParameterException()}
            }
        try{
            val response = client.get(url)
            if(response.status.isSuccess()){
                val votes :List<Vote> = response.body()

                //maps id to a mutable count
                val tempVotecount = mutableMapOf<String, Int>()

                //count votes
                votes.forEach {vote ->
                    //init to 1 if there is no entry for the party id
                    if(!tempVotecount.keys.any { it == vote.id }){
                        tempVotecount[vote.id] = 0
                    }
                    //we know the key is present in the map, it was just created^
                    tempVotecount[vote.id] = tempVotecount[vote.id]!!+1
                }
                tempVotecount.keys.forEach{id ->
                    receivedVotes.add(DistrictVotes(district, id, tempVotecount[id]?:0))
                }
            }else{
                throw Exception() //TODO
            }

        }catch (e:Exception){   //TODO this should be more specific
            e.printStackTrace()
        }finally {
            client.close()
        }
        return receivedVotes
    }
}