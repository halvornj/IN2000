package no.uio.ifi.in2000.halvorin.oblig2.model.alpacas

import kotlinx.serialization.Serializable

//helper class to conform to the endpoint standards
@Serializable
data class Parties(val parties: List<PartyInfo>)
