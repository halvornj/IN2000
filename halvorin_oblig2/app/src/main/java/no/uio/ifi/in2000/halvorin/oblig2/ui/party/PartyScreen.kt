package no.uio.ifi.in2000.halvorin.oblig2.ui.party

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartyScreen(
    navController: NavController,
    id:String,
    viewModel: PartyViewModel= viewModel(),
){
    val TAG = "PartyScreen"
    val partyState:PartyUiState by viewModel.partyUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("tilbake") },
                navigationIcon = {
                    IconButton(onClick = {
                        Log.d(TAG, "in back-onclick from PartyScreen/${id}")
                        navController.navigate("HomeScreen")
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Tilbake-pil")
                    }
                }
            )

        }
    )
    {innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 4.dp)
                .verticalScroll(rememberScrollState())

        ) {

            Text(text = partyState.partyInfo.name, fontSize = 35.sp)
            SubcomposeAsyncImage(
                model = partyState.partyInfo.img,
                contentDescription = "Image of our glorious leader",
                loading = { CircularProgressIndicator() },
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .clip(RoundedCornerShape(percent = 10))
                    .fillMaxWidth()
            )

            //TODO color-bar

            //DEBUG
            Log.d(TAG, partyState.partyInfo.toString())
            Log.d(TAG, partyState.partyInfo.color)



            Text(text = "Leder: ${partyState.partyInfo.leader}", fontSize = 25.sp)
            Divider(
                color = Color(android.graphics.Color.parseColor(partyState.partyInfo.color)),
                thickness =  15.dp
            )
            Text(text = partyState.partyInfo.description)
        }
    }
}


