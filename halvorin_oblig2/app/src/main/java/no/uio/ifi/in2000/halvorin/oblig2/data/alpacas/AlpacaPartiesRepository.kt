package no.uio.ifi.in2000.halvorin.oblig2.data.alpacas

import no.uio.ifi.in2000.halvorin.oblig2.model.alpacas.PartyInfo

class AlpacaPartiesRepository(private val source : AlpacaPartiesDataSource= AlpacaPartiesDataSource()) {

    private var parties : List<PartyInfo>? =null
    suspend fun parties():List<PartyInfo>{
        if (parties == null) {
            parties = source.getAlpacaPartiesData()
        }
        return parties as List<PartyInfo>
    }

    suspend fun getPartyInfo(id: Int):PartyInfo{
        return parties?.first { party -> party.id.toInt() == id }
            ?: parties().first { party -> party.id.toInt() == id }  //assuming unique IDs, no duplicates
    }
}