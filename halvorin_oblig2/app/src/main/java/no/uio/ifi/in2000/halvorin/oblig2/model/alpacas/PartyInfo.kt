package no.uio.ifi.in2000.halvorin.oblig2.model.alpacas

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable
import java.net.URL
@Serializable
data class PartyInfo(val id:String, val name:String, val leader:String, val img:String, val color:String, val description:String)
