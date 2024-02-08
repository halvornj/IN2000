package no.uio.ifi.in2000.halvorin.oblig2.data.alpacas

import no.uio.ifi.in2000.halvorin.oblig2.model.alpacas.PartyInfo

class AlpacaPartiesRepository(private val source : AlpacaPartiesDataSource= AlpacaPartiesDataSource()) {
    suspend fun parties():List<PartyInfo>{
        return source.getAlpacaPartiesData()
    }

}