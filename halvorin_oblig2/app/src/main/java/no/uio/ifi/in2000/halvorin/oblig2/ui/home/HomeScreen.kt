package no.uio.ifi.in2000.halvorin.oblig2.ui.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import no.uio.ifi.in2000.halvorin.oblig2.model.alpacas.PartyInfo
import no.uio.ifi.in2000.halvorin.oblig2.ui.home.HomeScreenViewModel



@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = viewModel(), navController: NavController){

    val partyInfoState : PartyInfoUiState by viewModel.partyInfoUiState.collectAsState()
    Column(
        modifier = Modifier.padding(3.dp)
    ) {
    Text(text = "Partier", fontSize = 45.sp)
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(partyInfoState.parties){partyInfo ->
            AlpacaCard(partyInfo = partyInfo)
        }
    }
    }
}


@Composable
fun AlpacaCard(partyInfo:PartyInfo){
    ElevatedCard(
        modifier = Modifier
            .padding(6.dp)
            .border(
                width = 5.dp,
                color = Color(android.graphics.Color.parseColor(partyInfo.color)),
                shape = ShapeDefaults.Medium
            ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = partyInfo.name)
            SubcomposeAsyncImage(
                model = partyInfo.img,
                loading = { CircularProgressIndicator()},
                contentDescription = "image of the party leader",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = "Leder: ${partyInfo.leader}")

        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun AlpacaCardPreview(){
    //mock data copied from the endpoint https://www.uio.no/studier/emner/matnat/ifi/IN2000/v24/obligatoriske-oppgaver/alpacaparties.json
    AlpacaCard(PartyInfo("1", "AlpacaNorth","Chewpaca","https://cdn.pixabay.com/photo/2019/06/24/10/42/alpaca-4295702_960_720.jpg","edb879","Chewpaca, med sin saftige, brune pels og navn inspirert av den berømte Star Wars-karakteren Chewbacca, er en uimotståelig alpakka født under de klare stjernehimmelen på et dyreelskende småbruk i Oregon.\\n\\nHan kom til verden med en bemerkelsesverdig høy statur og et ansikt så uttrykksfullt at det straks minnet gårdseierne om den kjære Wookiee krigeren. Chewpaca viste seg tidlig som en naturlig beskytter av de yngre og mindre alpakkaene på gården, og hans dype, nysgerrige brumming la til personligheten som allerede var like stor som hans filmiske motpart.\\n\\nKjærlig og leken, har han blitt lokalberømthet for sitt gode humør og for sin rolle i å gjøre gårdbesøk til et intergalaktisk eventyr for fans og familier som kommer for å møte denne stjernen fra alpakkaverdenen."))
}