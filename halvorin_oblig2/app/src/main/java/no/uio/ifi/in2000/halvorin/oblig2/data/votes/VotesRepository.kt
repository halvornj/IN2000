package no.uio.ifi.in2000.halvorin.oblig2.data.votes

import no.uio.ifi.in2000.halvorin.oblig2.model.votes.District
import no.uio.ifi.in2000.halvorin.oblig2.model.votes.DistrictVotes

class VotesRepository() {
    suspend fun getVotesForDistrict(district:District):List<DistrictVotes>{
        return if(district == District.DISTRICT_ONE||district == District.DISTRICT_TWO) {
            IndividualVotesDataSource().getVotesDistrict(district)
        } else{
            AggregatedVotesDataSource().getAggregatedVotes()
        }
    }
}