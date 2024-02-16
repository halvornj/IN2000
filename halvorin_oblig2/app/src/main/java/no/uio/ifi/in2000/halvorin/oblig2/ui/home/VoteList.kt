package no.uio.ifi.in2000.halvorin.oblig2.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import no.uio.ifi.in2000.halvorin.oblig2.model.alpacas.PartyInfo


@Composable
fun VoteList(voteCount: Map<String, Int>){
    //voteCount is purely partyName to Count of votes
    //counting and aggregation happens elsewhere. todo specify where
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(6.dp)
        ){
        item{
            Text(text = "Parti", fontSize = 20.sp)
        }
        item{ Text(text = "Antall Stemmer", fontSize = 20.sp)}
        voteCount.keys.forEach {key ->
            item{
                Text(text = key)
            }
            item {
                Text(text = voteCount[key].toString())
            }
        }
    }
}

@Preview(showSystemUi = true)
@Preview
@Composable
fun VoteListPreview(){
    VoteList(voteCount = mapOf("AlpacaNorth" to 7569, "AlpacaWest" to 7796, "AlpacaEast" to 9554, "AlpacaSouth" to 7953))
}