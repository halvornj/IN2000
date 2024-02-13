package no.uio.ifi.in2000.halvorin.oblig2.ui.party

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage


@Composable
fun PartyScreen(
    viewModel: PartyViewModel= viewModel(),
    navController: NavController,
    id:String
){
    val TAG = "PartyScreen"
    val partyState:PartyUiState by viewModel.partyUiState.collectAsState()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //TODO topbar
        Text(text = partyState.partyInfo.name, fontSize = 35.sp)
        SubcomposeAsyncImage(
            model = partyState.partyInfo.img,
            contentDescription = "Image of our glorious leader",
            loading = { CircularProgressIndicator()}
            //todo scale, need desktop for render of this
            )

    }

}


