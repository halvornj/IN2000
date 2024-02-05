package no.uio.ifi.in2000.halvorin.oblig2.model.alpacas

import androidx.compose.ui.graphics.Color
import java.net.URL

data class PartyInfo(val id:Int, val name:String, val leader:String, val imageUrl:URL, val color:Color, val description:String)
