package no.uio.ifi.in2000.halvorin.oblig2.data.alpacas

import no.uio.ifi.in2000.halvorin.oblig2.data.votes.IndividualVotesDataSource
import no.uio.ifi.in2000.halvorin.oblig2.data.votes.VotesRepository
import no.uio.ifi.in2000.halvorin.oblig2.model.alpacas.PartyInfo
import no.uio.ifi.in2000.halvorin.oblig2.model.votes.District
import no.uio.ifi.in2000.halvorin.oblig2.model.votes.DistrictVotes

class AlpacaPartiesRepository(private val source : AlpacaPartiesDataSource= AlpacaPartiesDataSource()) {

    private var parties : List<PartyInfo>? =null
    suspend fun parties():List<PartyInfo>{
        if (parties == null) {
            parties = source.getAlpacaPartiesData()
        }
        return parties as List<PartyInfo>
    }

    suspend fun getPartyInfo(id: String):PartyInfo{
        return parties?.first { party -> party.id == id }
            ?: parties().first { party -> party.id == id }  //assuming unique IDs, no duplicates
    }

    //returns a map from id to pair of PartyName and total vote count
    suspend fun getTotalPartyVotes():Map<String, Pair<String, Int>> {
        val votesRepo = VotesRepository()
        val allVotes = mutableListOf<DistrictVotes>()

        val finalMap = mutableMapOf<String, Pair<String, Int>>()
        District.entries.forEach { district ->
            allVotes.addAll(votesRepo.getVotesForDistrict(district))
        }
        allVotes.forEach { districtVote ->
            val id = districtVote.alpacaPartyId

            val oldTally = finalMap[id]?.second?:0
            finalMap[id] = Pair(
                finalMap[id]?.first ?:getPartyInfo(id).name, //use old name, or get name via other method if the id is not present in map yet
                oldTally+districtVote.numberOfVotesForParty //increment with old tally if already in map, else initializes to 0+first tally
            )
        }
        return finalMap
    }
}